package hy.constant;

public interface Constant {
	
	/*0启用; 1禁用; 2待审核; 3已审核 继续禁用与启用*/
	
	/*评论状态-待审核-2*/
	int COMMENT_STATUS_WAITING = 2;
	int PAGE_SIZE_COURSE = 5;
	/*评论状态-禁用-1*/
	int COMMENT_STATUS_ENABLE=1;


	/**
	 * 上次资源路径
	 */
	String RESOURCE_PATH = "path";
	/**
	 * 当前界面资源信息
	 */
	String RESOURCE = "resource";
	/**
	 * 资源ID
	 */
	String RESOURCE_ID = "resource_id";
	/*评论状态-已启用-0*/
	int COMMENT_STATUS_SUCCESS = 0;
	/**
	 * 课程ID
	 */
	String COURSE_ID = "course_id";
	/**
	 * 当前页面章节信息
	 */
	String CHAPTERS = "chapters";
	/**
	 * 错误信息
	 */
	String ERROR_MESSAGE = "errorMsg";
	/**
	 * 积分数
	 */
	int Point_Count_5 = 5;

	/**
	 * 启用状态
	 */
	int STATUS_ENABLE = 0;
	/**
	 * 签到成功后添加5积分
	 */
	String GoldPoints_Info = "签到成功后添加5积分";	
	/**
	 * 禁用状态
	 */
	int STATUS_DISABLE = 1;
	
	/**
	 * 积分
	 */
	int COST_TYPE_SCORE = 0;
	
	/**
	 * 金币
	 */
	int COST_TYPE_GOLD = 1;
	
	/**
	 * 课程推荐等级(0普通)
	 */
	int COURSE_RECOMMEND_LV0 = 0;
	
	/**
	 * 课程推荐等级(1推荐)
	 */
	int COURSE_RECOMMEND_LV1 = 1;
	
	/**
	 * 待审核状态-2
	 */
	int STATUS_WAITING = 2;
	
	/**
	 * 用户角色admin
	 */
	String USER_ROLE_ADMIN = "admin";
	
	/**
	 * 用户角色normal
	 */
	String USER_ROLE_NORMAL = "normal";
	
	/**
	 * 过滤器默认字符集
	 */
	String FILTER_CHARSET_UTF8 = "UTF-8";
	
	/**
	 * 获取文件上传路径的前缀
	 */
	String UPLOAD_PATH_PREFIX = "environment";
	
	/**
	 * 获取文件上传路径的后缀
	 */
	String UPLOAD_PATH_SUFFIX = ".path";
	
	/**
	 * 分页，每页条数
	 */
	int PAGE_SIZE = 2;
	
	
	/*--------------------------------------------------------------------以下为后来添加的常量-----------------------------------------------------------*/
	
	/**
	 * 分页，默认开始页
	 */
	int PAGE_START = 1;
	
	/**
	 * 分页，默认总页数
	 */
	int PAGE_TOTAL = 1;
	
	/**
	 * 后台，放在session中user名
	 */
	String SESSION_USER = "sessionUser";
	
	/**
	 * 后台，放在session中验证码值的名称
	 */
	String SESSION_CODE = "sessionCode";	
	
	/**
	 * 页面标签<select>第一个标签<option>的默认value值
	 */
	int SELECT_FIRST_VALUE = -1;
	
	/**
	 * 存放课程封面图片的根目录
	 */
	public static final String FOLDER_IMAGE_COURSE= "/../upload/netClass/courseImage/";
	
	/**
	 * 存放课程章节资源根目录
	 */
	public static final String FOLDER_CHAPTER_RESOURCE= "/../upload/netClass/chapterResource/";
	
	/**
	 * 后台，课程类别查询，专用的参数默认值
	 */
	String PARAMETER_COURSE_TYPE_IS_NULL = "-1";
	
	/**
	 * 后台，cookie的最大生存时间
	 */
	int COOKIE_AGE = 7*24*60*60;
	
	/**
	 * 后台，放在cookie中的登录名的key
	 */
	String COOKIE_LOGINNAME = "cookieLoginname";
	
	/**
	 * 后台，放在cookie中的密码的key
	 */
	String COOKIE_PASSWORD = "cookiePassword";
	
	/**
	 * 后台，放在cookie中的验证码的的key
	 */
	String COOKIE_CODE = "cookieCode";
	
	/**
	 * 后台，放在cookie中的密码加密值的的key
	 */
	String COOKIE_HAPPY = "happy";
	
	/**
	 * 可以在线播放的资源类型
	 */
	String RESOURSE_TYPE_ENJOY_ON_WEB = "mp4";
	
	/**
	 * 超级管理员的id，所有章节资源对应的userId都是这个值，也就是说，章节资源相关的金币积分收入，都进入这个账户
	 */
	int ADMIN_ID_SUPER = 1;
	
	/**
	 * 前台，放在session中user名
	 */
	String SESSION_USER_FRONT = "sessionUserFront";
	
	/**
	 *	session的最大生存时间
	 */
	int SESSION_AGE = 24*60*60;
	
}
