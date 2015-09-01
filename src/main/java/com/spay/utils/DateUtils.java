package com.spay.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;
import com.spay.utils.enums.DatePatternEnum;

public class DateUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    // @formatter:off
	private static Integer[] dateKeys = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY,
		Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND};



	// @formatter:on

    public static String getToday(DatePatternEnum pattern) {
        Date date = new Date();
        return format(date, pattern);
    }

    /**
     * @param date
     * @return
     */
    public static String format(Date date) {
        return sdf.format(date);
    }

    public static String format(Date date, DatePatternEnum pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern.getValue());
        return formatter.format(date);
    }

    /**
     * Gets the parts.
     *
     * @param date
     * @return
     */
    private static Map<Integer, Integer> extract(String date) {

        Pattern pattern = Pattern.compile("^([0-9]{4}).?([0-9]{1,2})?.?([0-9]{1,2})?\\s?([0-9]{1,2})?.?([0-9]{1,2})?.?([0-9]{1,2})?$");
        Matcher matches = pattern.matcher(date);

        if (matches.find()) {

            Map<Integer, Integer> result = Maps.newHashMap();
            Integer i, m, v, k;

            for (i = 0, m = dateKeys.length; i < m; i++) {
                k = i + 1;

                try {
                    v = Integer.parseInt(matches.group(k));
                } catch (Exception e) {
                    v = null;
                }

                result.put(dateKeys[i], dateKeys[i] == Calendar.MONTH && v != null ? --v : v);
            }

            return result;
        }

        return null;
    }

    /**
     * Gets the range.
     *
     * @param start
     * @param end
     * @return
     */
    public static Date[] getRange(String start, String end) {
        Date[] result = {getMinDate(start), getMaxDate(end)};
        return result;
    }

    /**
     * Gets the available minimum date.
     *
     * @param date
     * @return
     */
    public static Date getMinDate(String date) {

        Map<Integer, Integer> parts = extract(date);
        Calendar cal = Calendar.getInstance();

        Integer i, m, v, offset = null;
        for (i = 0, m = dateKeys.length; i < m; i++) {
            v = parts.get(dateKeys[i]);

            if (v != null) {
                cal.set(dateKeys[i], v);
            } else if (offset == null) {
                offset = i;
            }
        }

        if (offset != null) {
            for (i = offset, m = dateKeys.length; i < m; i++) {
                cal.set(dateKeys[i], cal.getMinimum(dateKeys[i]));
            }
        }

        return cal.getTime();
    }

    /**
     * Gets the available maximum date.
     *
     * @param date
     * @return
     */
    public static Date getMaxDate(String date) {

        Map<Integer, Integer> parts = extract(date);
        Calendar cal = Calendar.getInstance();

        Integer v, offset = null;
        for (int i = 0, m = dateKeys.length; i < m; i++) {
            v = parts.get(dateKeys[i]);

            if (v != null) {
                cal.set(dateKeys[i], v);
            } else if (offset == null) {
                offset = i;
            }
        }

        if (offset != null) {
            for (int i = offset, m = dateKeys.length; i < m; i++) {
                cal.set(dateKeys[i], cal.getMaximum(dateKeys[i]));
            }
        }

        return cal.getTime();
    }

    public static long getDiffDateMillis(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        startCal.setTime(startDate);
        endCal.setTime(endDate);

        long diffMillis = endCal.getTimeInMillis() - startCal.getTimeInMillis();
        // long difHour = (int)(diffMillis / (60 * 60 * 1000)); // 시간

        return diffMillis;
    }

    /**
     * 요일 구하기
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_WEEK);

        return day;
    }

    /**
     * 현재 시간 구하기
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int time = calendar.get(Calendar.HOUR_OF_DAY);

        return time;
    }

    /**
     * 포매팅 된 날짜를 Date 형으로 반환
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, DatePatternEnum pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern.getValue());
        return dateFormat.parse(date);
    }

    /**
     * 계산된 날짜를 Date 형으로 반환
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getCalculateDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(calendar.YEAR, year);
        calendar.add(calendar.MONTH, month);
        calendar.add(calendar.DATE, date);

        Date calculateDate = calendar.getTime();
        return calculateDate;
    }	
}
