package com.ts.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 型変換共通クラス<BR>
 * 移植元:
 * 		ファイル : DLLProject\DatabaseManager\clsDBFunc.vb
 * 		クラス   : Infomart.FoodsInfomart.DatabaseManager.clsDBFunc
 * 		ファイル : DLLProject\ComFuncLib\clsCommonFunc.vb
 * 		クラス   : Infomart.FoodsInfomart.ComFuncLib.clsCommonFunc
 *
 * @author hiSoft GaoHang 2011/10/28
 */
public class ConvUtil {

//    private static ResourceBundle bundle = ResourceBundle.getBundle("web.config");
    /**
     * データタイプを表す列挙。<br>
     * <ul>
     * <li>StringData ･･･ 文字型</li>
     * <li>NumericData ･･･ 数値型</li>
     * <li>DateData ･･･ 日付型</li>
     * <li>BoolData ･･･ bit型</li>
     * </ul>
     */
    public enum SqlDataType {

        StringData(0), NumericData(1), DateData(2), BoolData(3);
        private int value;

        private SqlDataType(int value) {
            this.value = value;
        }

        /**
         * 列挙値に対応する整数値を返す。
         *
         * @return 整数値
         */
        public int getValue() {
            return this.value;
        }

        /**
         * 整数値から対応する列挙値を取得する。
         *
         * @param value
         *            列挙に対応する整数値
         * @return 列挙
         */
        public static SqlDataType getEnum(int value) {
            switch (value) {
                case 0:
                    return StringData;
                case 1:
                    return NumericData;
                case 2:
                    return DateData;
                case 3:
                    return BoolData;
                default:
                    return StringData;
            }
        }
    }

    public static boolean convToBool(Object value) {
        if (value == null) {
            return false;
        }
        if ("1".equals(value.toString())) {
            return true;
        } else if ("0".equals(value.toString())) {
            return false;
        } else if ("true".equals(value.toString().toLowerCase())) {
            return true;
        } else if ("false".equals(value.toString().toLowerCase())) {
            return false;
        }
        return false;
    }

    /**
     * 処理する値をSQL用文字列に変換する。
     *
     * @param keyWord
     *            処理する文字列
     * @param dataType
     *            データタイプ
     * @param triming
     *            trim処理許可フラグ
     * @return 変換後の値
     * @author hiSoft GaoHang 2011/10/28
     */
    public static String convDataToDb(Object keyWord, SqlDataType dataType,
            boolean triming) {

        if (keyWord == null) {
            return "Null";
        }
        // データタイプをチェックする。
        switch (dataType) {
            case StringData:
                // 「""」の場合
                if (keyWord.toString().length() == 0) {
                    return "Null";
                }
                // 文字列データの場合、「'」を追加する。
                if (triming) {
                    // trimフラグがtrueの場合はtrim後の値を返す
                    return "'" + keyWord.toString().trim() + "'";
                } else {
                    return "'" + keyWord.toString() + "'";
                }
            case NumericData:
                // 数値データはそのまま返す
                return keyWord.toString();
            case BoolData:
                if (convToBool(keyWord)) {
                    return "1";
                } else {
                    return "0";
                }
            default:
                return "Null";
        }
    }

    public static String convDataToDb(Object keyWord) {
        return convDataToDb(keyWord, SqlDataType.StringData, true);
    }

    public static String convDataToDb(Object keyWord, SqlDataType dataType) {
        return convDataToDb(keyWord, dataType, true);
    }

    public static String convDataToDb(Object keyWord, boolean triming) {
        return convDataToDb(keyWord, SqlDataType.StringData, triming);
    }



    /**
     * 【SQLServer→Oracle移行時専用】引数pvntValが''の場合は"NULL"に変換、それ以外なら'で囲む<BR>
     *
     * @param pvntVal
     *            判定対象のオブジェクト
     * @param pintNotFlg
     *            「IS NULL」 か 「IS NOT NULL」 の判定フラグ
     * @return 判定後の文字列
     * @author hiSoft GaoHang 2011/10/28
     */
    public static String getSqlSetConvIsNull(String pvntVal, int pintNotFlg) {
        String retVal = "";
        if (pvntVal == null || "".equals(pvntVal)) {
            if (pintNotFlg == 1) {
                retVal = " IS NOT NULL ";
            } else {
                retVal = " IS NULL ";
            }
        } else {
            if (pintNotFlg == 1) {
                retVal = " <> '" + pvntVal + "'";
            } else {
                retVal = " = '" + pvntVal + "'";
            }
        }
        return retVal;
    }

    /**
     * 値をDouble型に変換します<BR>
     *
     * @param pValue
     *            判定値
     * @param pintNotFlg
     *            「IS NULL」 か 「IS NOT NULL」 の判定フラグ
     * @return Doubleに変換（変換できない場合は「0」を返します）
     * @author hiSoft GaoHang 2011/10/28
     */
    public static double convToDouble(Object pValue) {
        double dblValue = 0;
        if (pValue == null) {
            return dblValue;
        }

        try {
            dblValue = Double.parseDouble(String.valueOf(pValue).trim().replace(",", ""));
        } catch (Exception e) {
            return dblValue;
        }
        return dblValue;
    }

    /**
     * 値をBigDecimal型に変換します<BR>
     *
     * @param value
     *            判定値
     * @return Decimalに変換（変換できない場合は「0」を返します）
     * @author hiSoft GaoHang 2011/10/28
     */
    public static BigDecimal convToDec(Object value) {
        BigDecimal dec = new BigDecimal("0");

        if (value == null) {
            return dec;
        } else {
            try {
                if (value.getClass().isInstance(new Double(0))) {
                    dec = new BigDecimal((Double) value);
                } else {
                    dec = new BigDecimal(String.valueOf(value).trim().replace(",", ""));
                }
            } catch (Exception ex) {
                return dec;
            }
        }
        return dec;
    }

    /**
     * 値をLong型に変換します<BR>
     *
     * @param value
     *            判定値
     * @return Longに変換（変換できない場合は「0」を返します）
     * @author hiSoft GaoHang 2011/10/28
     */
    public static long convToLong(Object value) {
        long lngValue = 0;

        if (value == null) {
            return lngValue;
        } else {
            try {
                lngValue = Long.parseLong(value.toString().trim().replace(",", ""));
            } catch (NumberFormatException ex) {
                return lngValue;
            }
        }
        return lngValue;
    }

    /**
     * 値をint型に変換します<BR>
     *
     * @param value
     *            判定値
     * @return intに変換（変換できない場合は「0」を返します）
     * @author hiSoft GaoHang 2011/11/21
     */
    public static int convToInt(Object value) {
        int intValue = 0;
        //空データの場合
        if (value == null) {
            return intValue;
        } else {
            try {
                intValue = Integer.parseInt(value.toString().trim().replace(",", ""));
            } catch (NumberFormatException ex) {
            } finally {
                return intValue;
            }
        }
    }

    /**
     * 値をint型に変換します<BR>
     *
     * @param value
     *            判定値
     * @return intに変換（変換できない場合は「0」を返します）
     * @author hiSoft heweiwei 2011/11/21
     */
    public static BigInteger convToBigInteger(Object value) {
        BigInteger intValue = BigInteger.ZERO;
        //空データの場合
        if (value == null) {
            return intValue;
        } else {
            try {
                intValue = BigInteger.valueOf(convToLong(value));
            } catch (NumberFormatException ex) {
            } finally {
                return intValue;
            }
        }
    }

    public static short convToShort(Object value) {
        short retVal = new Short("0");
        if (value == null) {
            return retVal;
        } else {
            try {
                retVal = Short.parseShort(convToString(value));
            } catch (NumberFormatException ex) {
            } finally {
                return retVal;
            }
        }
    }

    /**
     * 指定の小数点以下処理を適用した整数値を返す。<BR>
     *
     * @param amount
     *            元の数値
     * @param belowDecPoint
     *            小数点以下処理方法　0：切捨て／1：切上げ／2：四捨五入（小数第1位）／3：四捨五入（小数第2位）
     * @return 小数点以下処理を適用した整数値
     * @author hiSoft GaoHang 2011/11/11
     */
    public static BigDecimal fixedAmount(BigDecimal amount, int belowDecPoint) {
        BigDecimal retValue = new BigDecimal("0");
        switch (belowDecPoint) {
            case 0:     //切捨て
                retValue = amount.setScale(belowDecPoint, RoundingMode.DOWN);
//                if (amount.intValue() > 0) {
//                    retValue = new BigDecimal(Math.floor(amount.doubleValue()));
//                } else {
//                    retValue = new BigDecimal(Math.ceil(amount.doubleValue()));
//                }
                break;
            case 1:     //切上げ
                retValue = amount.setScale(belowDecPoint, RoundingMode.UP);
//                if (amount.intValue() > 0) {
//                    retValue = new BigDecimal(Math.ceil(amount.doubleValue()));
//                } else {
//                    retValue = new BigDecimal(Math.floor(amount.doubleValue()));
//                }
                break;
            case 2:     //四捨五入（小数第2位)
                retValue = myRound(amount, 1);
                break;

            case 3:     //四捨五入（小数第1位）
                retValue = myRound(myRound(amount, 2), 1);
                break;
            default:
        }

        return retValue;
    }

    /**
     * 四捨五入を行う。<BR>
     *
     * @param value
     *            元の数値
     * @param beam
     *            1:小数第1位で四捨五入, 2:小数第2位で四捨五入
     * @return 四捨五入後の値
     * @author hiSoft GaoHang 2011/11/11
     */
    public static BigDecimal myRound(BigDecimal value, int beam) {
        BigDecimal ret = new BigDecimal("0");
        switch (beam) {
            case 2:
                ret = value.setScale(1, RoundingMode.HALF_UP);
                break;
            case 1:
                ret = value.setScale(0, RoundingMode.HALF_UP);
                break;
            default:
                break;
        }

        return ret;
    }

    /**
     * 数値のコンマ編集をする(formatNumber関数を参照)。<BR>
     *
     * @param val
     *            値
     * @param point
     *            小数部
     * @return "9,999."、"9,999.999"
     * @author hiSoft GaoHang 2011/11/11
     */
    public static String formatNumberSm(String val, int point) {
        // 編集値
        String rtn;
        if (StringUtils.isEmpty(val)) {
            return rtn = "";
        }
        rtn = String.format("%1$,." + point + "f", Double.parseDouble(val));
        if (convToDec(val).compareTo(BigDecimal.ZERO) < 0) {
            rtn = "<span style=\"color: red\">" + rtn + "</span>";
        }
        return rtn;
    }

    /**
     * テーブルコラム定数から、SQL文を作成する。<BR>
     *
     * @param columnNames
     *            テーブルのコラム
     * @return コラムSQL文
     * @author hiSoft GaoHang 2011/11/11
     */
    public static StringBuilder convToColumnsSql(String[] columnNames) {
        StringBuilder sbSql = new StringBuilder();
        int intStep = 0;
        for (String columnName : columnNames) {
            if (intStep == 0) {
                sbSql.append(columnName);
            } else {
                sbSql.append(",").append(columnName);
            }
            intStep++;
        }
        return sbSql;
    }

    /**
     * 日付時間式を指定形式でフォーマットする。<BR>
     *
     * @param pdtmExpression
     *            入力値
     * @param pstrFormatText
     *            フォーマット
     * @return 指定形式日付
     * @author hiSoft GaoHang 2011/11/26
     */
    public static String convertDateTime(String pdtmExpression, String pstrFormatText) {
        return convertDateTime(pdtmExpression, pstrFormatText, null);
    }

    /**
     * 日付時間式を指定形式でフォーマットする。<BR>
     *
     * @param pdtmExpression
     *            入力値
     * @param pstrFormatText
     *            フォーマット
     * @param locale
     *             ローカル
     * @return 指定形式日付
     * @author hiSoft GaoHang 2011/11/26
     */
    public static String convertDateTime(String pdtmExpression, String pstrFormatText, Locale locale) {
        String formatDate = "";
        //String.format("%1$,." + point + "f", Double.parseDouble(val));
        if (pstrFormatText.trim().isEmpty()) {
            pstrFormatText = "yyyy-MM-dd hh:mm:ss";
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }

        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar calendar = Calendar.getInstance(locale);
        try {
            calendar.setTime(sdf.parse(pdtmExpression));
            sdf.applyPattern(pstrFormatText);
            formatDate = sdf.format(calendar.getTime());
        } catch (ParseException ex) {
            formatDate = "";
        }
        return formatDate;
    }

    /**
     * 文字列Collectionから指定した区切りデータを作成。<BR>
     *
     * @param pCollection
     *            文字列Collection
     * @param pDelimiter
     *            区切り記号
     * @return CSV形式（string）
     * @author hiSoft GaoHang 2011/11/26
     */
    public static String createSeparateData(Collection<String> pCollection, String pDelimiter) {
        StringBuilder sbCsvData = new StringBuilder();
        int intStep = 0;
        for (String item : pCollection) {
            if (intStep > 0) {
                sbCsvData.append(pDelimiter);
            }
            sbCsvData.append(item);
            intStep++;
        }
        return sbCsvData.toString();
    }

    /**
     * 文字列CollectionからCSV形式のデータを作成。<BR>
     *
     * @param pCollection
     *            文字列Collection
     * @return CSV形式（string）
     * @author hiSoft GaoHang 2011/11/26
     */
    public static String createCSVData(Collection<String> pCollection) {
        return createSeparateData(pCollection, ",");
    }

    /**
     * SSL 通信か否かを判定し、Bool 値を返す。<BR>
     * 移植元：
     * 		関数：IsSSL
     *
     * @return SSL 通信か否かを判定し、Bool 値を返す。
     * @author hiSoft GaoHang 2011/11/26
     */
    public static boolean isSSL() {
        //Return (Current.Request.ServerVariables("SERVER_PORT") = "81" OrElse Current.Request.ServerVariables("SERVER_PORT") = "443")
        return false;
    }

    /**
     * 数値を加工して文字列へ変換。<BR>
     * 移植元：
     * 		関数：GetStrNumber
     * @param expression
     *            対象式
     * @param numberOfDecimals
     *            小数点以下桁数
     * @param delimiter
     *            区切り文字
     * @return 整形後の文字列。
     * @author hiSoft GaoHang 2011/11/26
     */
    public static String getStrNumber(String expression, int numberOfDecimals, boolean delimiter) {
        String formatNumber = "";
        if (!StringUtils.isEmpty(expression)) {
            try {
                if (delimiter) {
                    formatNumber = String.format("%1$,." + numberOfDecimals + "f", Double.parseDouble(expression));
                } else {
                    formatNumber = String.format("%1$." + numberOfDecimals + "f", Double.parseDouble(expression));
                }
            } catch (NumberFormatException ex) {
                formatNumber = expression;
            }
        } else {
            formatNumber = "&nbsp;";
        }
        return formatNumber;
    }

    /**
     * 文字列から無効な文字を削除した結果を返す。<BR>
     * 移植元：
     * 		関数：Infomart.FoodsInfomart.ComFuncLib.clsComFuncString.IgnoreString
     * @param keyWord
     *          処理する文字列
     * @return 無効な文字を削除した結果
     * @author hiSoft GaoHang 2011/12/05
     */
    public static String ignoreString(String keyWord) {
        //下記正規表現と一致した文字は、空白に置き換える
        if (!StringUtils.isEmpty(keyWord)) {
            return keyWord.replaceAll("([%\\^*()'<>,\"\t\\[\\]])", "");
        } else {
            return "";
        }
    }


    /**
     * 文字列形式のデータをHTMLとして表示する際の基本的な処理 + α。<BR>
     * 移植元：
     * 		関数：clsCommon.HtmlEnc
     * @param value
     *          HTMLエンコード + αする文字列
     * @return HTMLエンコード + αされた文字列
     * @author hiSoft GaoHang 2011/12/07
     */
    public static String htmlEnc(String value) {
        //IEのテーブル枠は<TD>が空の場合は消えるので、NULLと空文字に対しては &nbsp; を返す
        if (StringUtils.isEmpty(value)) {
            return "&nbsp;";
        } else {
//            value = StringEscapeUtils.escapeHtml(value);
            if (StringUtils.isEmpty(value) || StringUtils.isEmpty(value.trim())) {
                return "&nbsp;";
            }
            return value.replace("\n", "<br />");
        }
    }

    public static List<String> getSelectColumns(List<String> lstColumn) {
        List<String> lstSelectColumns = new ArrayList<String>();

        for (String item : lstColumn) {
            lstSelectColumns.add(getColumnName(item));
        }

        return lstSelectColumns;
    }

    public static String getColumnName(String baseText) {
        String strColumnName = "";

        if (baseText.toUpperCase().contains(" AS ")) {
            String colName = "";
            colName = baseText.substring(baseText.toUpperCase().lastIndexOf(" AS ") + 4);
            strColumnName = colName.trim().toUpperCase();
        } else {
            String[] aryCol = baseText.split("\\.");
            strColumnName = aryCol[aryCol.length - 1].toUpperCase().trim();
        }
        return strColumnName;
    }

    public static List<Map<String, Object>> convObjectListToMapList(List<Object[]> lstObject, List<String> lstColumn) {
        List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();

        List<String> lstColName = ConvUtil.getSelectColumns(lstColumn);
        for (Object[] aryObj : lstObject) {
            Map<String, Object> mpItem = new HashMap<String, Object>();
            for (int i = 0; i < lstColName.size(); i++) {
                mpItem.put(lstColName.get(i), aryObj[i]);
            }
            lstMap.add(mpItem);
        }

        return lstMap;
    }

    /**
     * 値をString型に変換します。
     *
     * @param obj 入力文字列
     * @return String型の値
     */
    public static String convToString(Object obj) {
        if (obj == null) {
            return "";
        }
        if ( StringUtils.isEmpty(obj.toString())) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 空文字列判定
     *
     * @param input 入力文字列
     * @return true/false
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文字列の前後のスペース（半角、全角）を取り除くメソッドです。
     *
     * @param orgstr 変換対象の文字列
     * @return 変換済みの文字列
     */
    public static String trim(String orgstr) {
        if (StringUtils.isEmpty(orgstr)) {
            return "";
        }
        /* 前方のスペースを取り除く */
        while (orgstr.startsWith(" ") || orgstr.startsWith("　")) {
            orgstr = orgstr.substring(1);
        }
        /* 後方のスペースを取り除く */
        while (orgstr.endsWith(" ") || orgstr.endsWith("　")) {
            orgstr = orgstr.substring(0, orgstr.length() - 1);
        }
        return orgstr;
    }

    /**
     * 文字列の左端から指定したバイト数分の文字列を返します<BR>
     * 移植元：
     * 		関数：clsComFuncString.VBLeftB
     * @param value 対象値
     * @param length 取り出すバイト数
     * @return 左端から指定されたバイト数分の文字列
     * @author hiSoft GaoHang 2012/02/16
     */
    public static String leftB(String value, int length) {
        StringBuilder sbText = new StringBuilder();
        String stepData = "";
        int byteCnt = 0;
        int pos = 0;

        if (!isEmpty(value) && value.trim().length() > 0) {
            while (pos < value.length()) {
                //1文字づつ取得
                stepData = value.substring(pos, pos + 1);
                try {
                    byteCnt += value.substring(pos, pos + 1).getBytes("Shift_JIS").length;
                    if (byteCnt > length) {
                        break;
                    }
                    sbText.append(stepData);
                    pos++;
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return sbText.toString();
    }



    /**
     * CSVファイルのヘッダ作成処理。<BR>
     * @param headerNames ヘッダ名配列
     * @param hiddenStep 非表示の項目配列
     * @return CSVファイルのヘッダ
     * @author hiSoft GaoHang 2012/03/06
     */
    public static String createCsvHeader(String[] headerNames, Collection<Integer> hiddenSteps) {
        StringBuilder sbHeader = new StringBuilder();
        int intStep = 0;
        boolean isHiddenHeader = false;
        for (String headerNm : headerNames) {
            isHiddenHeader = false;
            for (int hiddenStep : hiddenSteps) {
                if (intStep == hiddenStep) {
                    isHiddenHeader = true;
                    break;
                }
            }
            intStep ++;
            if (isHiddenHeader) {
                continue;
            }
            if (sbHeader.length() == 0) {
                sbHeader.append("\"").append(headerNm).append("\"");
            } else {
                sbHeader.append(",\"").append(headerNm).append("\"");
            }
        }
        return sbHeader.toString();
    }

    public static String convClobToString(Clob clob) {
        if (clob == null) {
            return null;
        }
        try{
            Reader reader = clob.getCharacterStream();
            char[] tempChar = new char[(int)clob.length()];
            reader.read(tempChar);
            reader.close();
            return new String(tempChar);
        }catch(IOException e){


        }catch(SQLException es){

        }
        return null;
    }

    /**
     * 数字转化为人民币大写
     * @param obj
     * @return
     */
	public static String convToRmb(Object obj) {

		double value = convToDouble(obj);

		if (value == 0) {
			return "零";
		}

		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}
}
