package util;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;

@UtilityClass
public class UrlPath {
    public static final String LOGIN = "/login"; //Public
    public static final String REGISTRATION = "/registration"; //Public
    public static final String IMAGES = "/users"; //User
    public static final String LOCALE = "/locale"; //User
    public static final String ROOM_INFO = "/roominfo"; //User
    public static final String ORDER = "/order"; //User
    public static final String ORDER_DONE = "/orderdone"; //User
    public static final String MAIN_PAGE = "/mainpage"; //User
    public static final String ADMIN_PAGE = "/adminpage"; //Admin
    public static final String ADD_ROOM = "/addroom"; //Admin
    public static final String LOGOUT = "/logout"; //Admin/USER
    public static final String USER_ORDER_LIST = "/userorderlist"; //Admin/USER
    public static final String SEE_INFO_ABOUT_ORDER = "/see_info_about_order";
    public static final String DOWNLOAD_USER_REPORT = "/download_user_report";
    public static final String DOWNLOAD_ADMIN_REPORT = "/download_admin_report";
    public static final String FIND_ALL_ROOMS = "/findallrooms";
    public static final String FIND_ALL_FREE_ROOM = "/findallfreeroom";
    public static final String CHECK_ORDER = "/checkorder";
    public static final String SEE_ALL_ORDERS_FOR_ADMIN = "/seeallordersforadmin";
    public static final String CHECK_ORDER_EXISTING = "/checkorderexisting";
    public static final Path USER_REPORT_FULL_PATH = Path.of("C:", "Users", "User", "IdeaProjects", "hotel", "resources", "user_report.txt");
    public static final Path ADMIN_REPORT_FULL_PATH = Path.of("C:", "Users", "User", "IdeaProjects", "hotel", "resources", "admin_report.txt");
}