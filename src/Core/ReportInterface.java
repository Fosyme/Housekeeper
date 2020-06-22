package Core;

import Dao.OrderOperation;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportInterface {
    private User user;

    public ReportInterface(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式

        Calendar calendar = Calendar.getInstance();// 获取当前日期

        long time2 = thisDayStartCalender(calendar).getTimeInMillis();
        long time3 = thisWeekStartCalender(calendar).getTimeInMillis();
        long time4 = thisMonthStartCalender(calendar).getTimeInMillis();
        long time5 = thisYearStartCalender(calendar).getTimeInMillis();

        System.out.println("now time: \t\t" + sdf.format(time1) + " " + time1);
        System.out.println("day time: \t\t" + sdf.format(time2) + " " + time2);
        System.out.println("week time: \t\t" + sdf.format(time3) + " " + time3);
        System.out.println("month time: \t" + sdf.format(time4) + " " + time4);
        System.out.println("year time: \t" + sdf.format(time5) + " " + time5);
    }

    /**
     * 报表数据生成
     *
     * @param bookIndex 账本索引
     * @param nowTime 当前时间时间戳
     * @return 饼状图数据
     */
    public List<List<PieChart.Data>> reportData(int bookIndex, long nowTime) {
        String bookID = user.getBooks().get(bookIndex).getBookID();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(nowTime);
        long startDayTime = thisDayStartCalender(calendar).getTimeInMillis();
        long endDayTime = nextDayStartCalender(calendar).getTimeInMillis() - 1;
        long startWeekTime = thisWeekStartCalender(calendar).getTimeInMillis();
        long endWeekTime = nextWeekStartCalender(calendar).getTimeInMillis() - 1;
        long startMonthTime = thisMonthStartCalender(calendar).getTimeInMillis();
        long endMonthTime = nextMonthStartCalender(calendar).getTimeInMillis() - 1;
        long startYearTime = thisYearStartCalender(calendar).getTimeInMillis();
        long endYearTime = nextYearStartCalender(calendar).getTimeInMillis() - 1;
        ResultSet dayRS = OrderOperation.queryTimeInterval(bookID, startDayTime / 1000, endDayTime / 1000);
        ResultSet weekRS = OrderOperation.queryTimeInterval(bookID, startWeekTime / 1000, endWeekTime / 1000);
        ResultSet monthRS = OrderOperation.queryTimeInterval(bookID, startMonthTime / 1000, endMonthTime / 1000);
        ResultSet yearRS = OrderOperation.queryTimeInterval(bookID, startYearTime / 1000, endYearTime / 1000);
        List<List<PieChart.Data>> list = new ArrayList<>();
        list.add(writeData(dayRS));
        list.add(writeData(weekRS));
        list.add(writeData(monthRS));
        list.add(writeData(yearRS));
        return list;
    }

    private List<PieChart.Data> writeData(ResultSet rs) {
        List<PieChart.Data> data = new ArrayList<>();
        try {
            double out = 0;
            double in = 0;
            while (rs.next()) {
                if (rs.getString("order_mod").equals("支出")) {
                    out += rs.getDouble("order_price");
                }
                if (rs.getString("order_mod").equals("收入")) {
                    in += rs.getDouble("order_price");
                }
            }
            data.add(new PieChart.Data("支出" + out, out));
            data.add(new PieChart.Data("收入" + in, in));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //获取当天0点时间戳
    private static Calendar thisDayStartCalender(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    //获取下一天0点时间戳
    private static Calendar nextDayStartCalender(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar;
    }

    //获取当周0点时间戳
    private static Calendar thisWeekStartCalender(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        thisDayStartCalender(calendar);
        return calendar;
    }

    //获取下一周0点时间戳
    private static Calendar nextWeekStartCalender(Calendar calendar) {
        calendar.add(Calendar.WEEK_OF_MONTH, 1);
        return calendar;
    }

    //获取当月0点时间戳
    private static Calendar thisMonthStartCalender(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1); //设置为1号,当前日期既为本月第一天
        thisDayStartCalender(calendar);
        return calendar;
    }

    //获取下一月0点时间戳
    private static Calendar nextMonthStartCalender(Calendar calendar) {
        calendar.add(Calendar.MONTH, 1);
        return calendar;
    }

    //获取当年0点时间戳
    private static Calendar thisYearStartCalender(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        thisDayStartCalender(calendar);
        return calendar;
    }

    //获取下一年0点时间戳
    private static Calendar nextYearStartCalender(Calendar calendar) {
        calendar.add(Calendar.YEAR, 1);
        return calendar;
    }
}
