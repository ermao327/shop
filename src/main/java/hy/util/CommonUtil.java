package hy.util;


import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 工具类 
 */
public class CommonUtil {

	private static String uploadPath;
	/**
	 * 文件请求路径
	 */
	private static String contextPath;
	/**
	 * 项目应用application
	 */
	private static ServletContext context;

	
	/**
	 * 获取文件上传的路径(如果文件夹不存在,则自动创建)
	 * 
	 * @return
	 */
	public static String getUploadPath() {
		//IDEA使用以下方式获取不到路径
		//String realPath = getContext().getRealPath(uploadPath);
		
		String realPath = getContext().getRealPath("/") + uploadPath;
		File f = new File(realPath);
		if (!f.exists()) {
			boolean flag = f.mkdirs();
			if (!flag) {
				throw new IllegalArgumentException("文件上传路径创建失败:" + realPath);
			}
		}
		return f.getAbsolutePath();
	}

	/**
	 * 获取文件请求路径
	 * 
	 * @return
	 */
	public static String getContextPath() {
		if (null == contextPath) {
			contextPath = getContext().getContextPath() + "/" + uploadPath;
		}
		return contextPath;
	}

	public static ServletContext getContext() {
		if (null == context) {
			throw new RuntimeException("项目启动失败,或者项目已停止");
		}
		return context;
	}

	public static void setContext(ServletContext context) {
		CommonUtil.context = context;
	}

	/**
	 * @see Random 随机类
	 */
	public static Random r = new Random();

	/**
	 * 生成随机颜色
	 * 
	 * @return java.awt.Color
	 */
	public static Color randomColor() {
		Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		return color;
	}

	public static final String[] FONT_NAMES = { "宋体", "楷书", "仿宋", "黑体", "微软雅黑" };
	public static final int[] FONT_STYLES = { Font.BOLD, Font.ITALIC,
			Font.PLAIN, Font.BOLD + Font.ITALIC };

	/**
	 * 生成随机字体
	 * 
	 * @return java.awt.Font
	 */
	public static Font randomFont() {
		String name = FONT_NAMES[r.nextInt(FONT_NAMES.length)];
		int style = FONT_STYLES[r.nextInt(FONT_STYLES.length)];
		int size = 30;
		Font f = new Font(name, style, size);
		return f;
	}

	/**
	 * 随机产生几个字符
	 * 
	 * @param counts
	 * @return java.lang.String
	 */
	public static String randomCode(int counts) {
		String words = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random r = new Random();
		StringBuffer code = new StringBuffer();
		for (int i = 0; i < counts; i++) {
			int index = r.nextInt(words.length());
			code.append(words.charAt(index));
		}
		return code.toString();
	}

	/**
	 * 随机产生一个有四个中文的字符串
	 * 
	 * @return java.lang.String
	 */
	public static String randomCode() {
		int len = words.length;
		if (0 == len) {
			return null;
		}
		return words[r.nextInt(len)];
	}

	/**
	 * 生成验证码
	 * 
	 * @param backColor
	 *            背景颜色
	 * @param counts
	 *            验证码中的字数(字数越多,图片宽度越大,最小4个,width最小80,height30)
	 * @param isChinese
	 *            是否选择中文验证码(使用时无法指定字符数量)
	 * @param hasLines
	 *            是否有干扰线
	 * @return com.itany.netClass.util.CommonUtil.MyImage(width:auto,height:40)
	 */
	public static MyImage getImage(Color backColor, int counts,
			boolean isChinese, boolean hasLines) {
		MyImage m = CommonUtil.getImage(backColor, false, null, counts,
				isChinese, hasLines);
		return m;
	}

	/**
	 * 生成验证码
	 * 
	 * @param backColor
	 *            背景颜色
	 * @param codes
	 *            验证码,其中的字数越多,图片宽度越大,width动态,height30
	 * @param hasLines
	 *            是否有干扰线
	 * @return com.itany.netClass.util.CommonUtil.MyImage(width:auto,height:40)
	 */
	public static MyImage getImage(Color backColor, String codes,
			boolean hasLines) {
		if (null == codes || "".equals(codes.trim())) {
			return null;
		}
		MyImage m = CommonUtil.getImage(backColor, true, codes, 0, true,
				hasLines);
		return m;
	}

	/**
	 * 会随机生成4条干扰线
	 * 
	 * @param backColor
	 *            背景颜色,可以没有
	 * @param isUseCode
	 *            是否使用自定义code
	 * @param codeString
	 *            自定义code字符串
	 * @param counts
	 *            不使用自定义code或中文成语时,指定生成的字符数量
	 * @param isChinese
	 *            是否生成中文成语字符串
	 * @param hasLines
	 *            是否有干扰线
	 * @return com.itany.netClass.util.CommonUtil.MyImage(width:auto,height:40)
	 */
	private static MyImage getImage(Color backColor, boolean isUseCode,
			String codeString, int counts, boolean isChinese, boolean hasLines) {
		// width 124 刚好4个字符,每多一个增加18像素
		int width = 118;
		int height = 40;
		int offWidth = 24;// width偏移量
		int lines = 4;// 干扰线的数量
		String codeStr = null;// code字符串
		if (counts < 4) {
			counts = 4;
		}
		if (isUseCode) {// 使用自定义code
			codeStr = codeString;
			counts = codeStr.length();
		}
		if (isChinese) {
			counts = 4;
			width += 6;// 中文字符较大
		}
		width = width + (counts - 4) * offWidth;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 背景色
		if (null == backColor) {
			backColor = new Color(0xffeded);
		}
		g.setColor(backColor);
		g.fillRect(0, 0, width, height);
		if (!isUseCode) {// 不使用自定义code字符串时
			if (isChinese) {
				codeStr = CommonUtil.randomCode();
			} else {
				codeStr = CommonUtil.randomCode(counts);
			}
		}
		if (null == codeStr) {// 如果没有codeStr,默认使用以下方式
			codeStr = CommonUtil.randomCode(counts);
		}
		StringBuffer codes = new StringBuffer();
		for (int i = 0; i < counts; i++) {
			// 每个字符单独设置样式
			String code = String.valueOf(codeStr.charAt(i));
			codes.append(code);
			g.setColor(CommonUtil.randomColor());
			g.setFont(CommonUtil.randomFont());
			// x根据情况调整,y和字体大小保持一致
			g.drawString(code, 12 + (i * offWidth), 30);
		}
		// x的范围(0~10),y的范围(width-10~width)
		int xRange = 20;
		int yRange = width - xRange;
		if (!hasLines) {// 不需要干扰线
			lines = -1;
		}
		for (int i = 0; i < lines; i++) {
			g.setColor(Color.BLACK);// 默认黑色,无其他样式
			int x1 = r.nextInt(xRange);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(xRange) + yRange;
			int y2 = r.nextInt(height);
			// System.out.println("x1="+x1+",y1="+y1+"=="+"x2="+x2+",y2="+y2);
			g.drawLine(x1, y1, x2, y2);
		}

		MyImage m = new MyImage(image, codes.toString());
		return m;
	}

	// 存放从以下文件中读取的验证码字符串
	private static String[] words;
	static {
		loadCodeWords("code-words.txt");
	}

	private static void loadCodeWords(String path) {
		BufferedReader br = null;
		List<String> list = new ArrayList<String>();
		try {
			InputStream is = CommonUtil.class.getClassLoader()
					.getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			//验证码字符串文件code-words.txt加载失败
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		words = new String[list.size()];
		if (!list.isEmpty()) {
			list.toArray(words);
		}
	}

	/**
	 * 封装image和code
	 */
	public static class MyImage {
		private BufferedImage image;
		private String code;

		public MyImage() {
		}

		public MyImage(BufferedImage image, String code) {
			this.image = image;
			this.code = code;
		}

		public BufferedImage getImage() {
			return image;
		}

		public void setImage(BufferedImage image) {
			this.image = image;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

	private static final String[] DIGITS = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * md5算法处理
	 * 
	 * @param str
	 *            原字符串
	 * @return java.lang.String 长度32
	 */
	public static String md5(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] bs = md5.digest(str.getBytes("utf-8"));
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < bs.length; i++) {
				buffer.append(DIGITS[bs[i] >> 4 & 0x0F]).append(
						DIGITS[bs[i] & 0x0F]);
			}
			// 转大写
			return buffer.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把请求中的参数封装成指定的对象<br>
	 * 可以自动把Date字符串转换Date类型<br>
	 * commons-beanutils工具方法<br>
	 * @param request 请求对象
	 * @param obj 需要封装的对象
	 * @param datePattern 日期格式
	 */

	/**
	 * 把请求中的参数封装成指定的对象<br>
	 * 可以自动把Date字符串转换Date类型<br>
	 * 模仿commons-beanutils工具的方法
	 * @param paramsMap 请求参数map
	 * @param obj 需要封装的对象
	 * @param datePattern 日期格式
	 */
	public static void getObj(Map<String, String[]> paramsMap, Object obj, String datePattern) {
		Set<String> keySet = paramsMap.keySet();
		for (String key : keySet) {
			String[] arr = paramsMap.get(key);
			if (null != arr && arr.length > 0 && null != arr[0] && !"".equals(arr[0].trim())) {
				// params.put(key, arr[0]);
				if (null == key || "".equals(key)) {
					continue;
				}
				if (key.contains(".")) {// 含有点
//					System.out.println("key=" + key);// a.b.c
					String[] split = key.split("\\.");
					int index = 0;
					Class<?> c = obj.getClass();
					try {
						Object fieldValue = obj;
						Field f = null;
						Class<?> type = null;
						int count = split.length - 1;
						while (index < split.length) {
							f = c.getDeclaredField(split[index]);
							type = f.getType();
//							System.out.println("fieldName=" + f.getName());
							String getterName = "get"
									+ f.getName().substring(0, 1).toUpperCase()
									+ f.getName().substring(1);
							Method getter = c.getMethod(getterName);
							c = type;
							if (index < count) {
								fieldValue = getter.invoke(fieldValue);
							}
							index++;
						}
//						System.out.println("invoke=" + fieldValue + "="
//								+ f.getName() + "=" + arr[0]);
						setProperty(fieldValue, f.getName(), arr[0], datePattern);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					continue;
				}
				setProperty(obj, key, arr[0], datePattern);
			}
		}
	}

	private static void setProperty(Object obj, String filedName, String value, String datePattern) {
		Class<?> c = obj.getClass();
		Field f = null;
		try {
			f = c.getDeclaredField(filedName);
		} catch (NoSuchFieldException e) {
			// e.printStackTrace();
		} catch (SecurityException e) {
			// e.printStackTrace();
		}
		if (null != f) {
			Class<?> type = f.getType();
			String setter = "set" + filedName.substring(0, 1).toUpperCase()
					+ filedName.substring(1);
//			System.out.println("setter= " + setter + ",parameterTypes=" + type);
			try {
				Method method = c.getMethod(setter, type);
				Object args = null;
				int typeKey = 0;
				if (byte.class == type || Byte.class == type) {
					typeKey = 1;
				} else if (short.class == type || Short.class == type) {
					typeKey = 2;
				} else if (char.class == type || Character.class == type) {
					typeKey = 3;
				} else if (int.class == type || Integer.class == type) {
					typeKey = 4;
				} else if (long.class == type || Long.class == type) {
					typeKey = 5;
				} else if (float.class == type || Float.class == type) {
					typeKey = 6;
				} else if (double.class == type || Double.class == type) {
					typeKey = 7;
				} else if (boolean.class == type || Boolean.class == type) {
					typeKey = 8;
				} else if (String.class == type) {
					typeKey = 9;
				} else if (Date.class == type) {
					typeKey = 10;
				}
				// java.sql.Date
				// java.sql.Time
				// java.sql.Timestamp
				// 不是字符串则不能为null或者空
				if ((0 != typeKey || 9 != typeKey)
						&& (null == value || "".equals(value))) {
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
				switch (typeKey) {
				case 1:
					// byte
					try {
						args = Byte.parseByte(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					// short
					try {
						args = Short.parseShort(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					// char
					try {
						args = value.charAt(0);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					// int
					try {
						args = Integer.parseInt(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					// long
					try {
						args = Long.parseLong(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					// float
					try {
						args = Float.parseFloat(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					// double
					try {
						args = Double.parseDouble(value);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					break;
				case 8:
					// boolean
					args = Boolean.parseBoolean(value);
					break;
				case 9:
					// String
					args = value;
					break;
				case 10:
					// java.util.Date
					try {
						args = sdf.parse(value);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				default:

					break;
				}
				if (null != args) {
					method.invoke(obj, args);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}


}
