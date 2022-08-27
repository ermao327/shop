package hy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ajax方法统一返回对象
 * @author teacher
 * @date 2018-8-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {

	/**
	 * 执行操作是否成功
	 */
	private boolean success;
	/**
	 * 操作执行后的提示
	 */
	private String msg;
	/**
	 * 执行操作后需要传递到页面的数据
	 */
	private Object obj;

	
}
