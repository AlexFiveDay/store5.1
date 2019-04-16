package cn.tedu.store5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store5.controller.ex.FileContentTypeException;
import cn.tedu.store5.controller.ex.FileEmptyException;
import cn.tedu.store5.controller.ex.FileIOException;
import cn.tedu.store5.controller.ex.FileIllegalStateException;
import cn.tedu.store5.controller.ex.FileSizeException;
import cn.tedu.store5.controller.ex.FileUploadException;
import cn.tedu.store5.service.ex.AccessDeniedException;
import cn.tedu.store5.service.ex.AddressNotFoundException;
import cn.tedu.store5.service.ex.CartNotFoundException;
import cn.tedu.store5.service.ex.InsertException;
import cn.tedu.store5.service.ex.PasswordNotMatchException;
import cn.tedu.store5.service.ex.ServiceException;
import cn.tedu.store5.service.ex.UpdateException;
import cn.tedu.store5.service.ex.UserNotFoundException;
import cn.tedu.store5.service.ex.UsernameDuplicateException;
import cn.tedu.store5.service.ex.deleteException;
import cn.tedu.store5.util.ResponseResult;

public class BaseController {
	/**
	 * 响应结果状态:成功
	 */
	public static final Integer SUCCESS = 200;

	/**
	 * 从浏览器的session中获取uid
	 * 
	 * @param session
	 * @return
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.parseInt(session.getAttribute("uid").toString());
	}

	/**
	 * 可以添加异常处理范围，不填写，则所有异常都会被其处理，造成类型传换问题， 方法括号中的异常范围必须大于注解中的范围。
	 * 写在单个控制器则此方法作用范围仅限于此控制器。 一般写在控制器基类中，用于其他控制器继承，增加作用范围。
	 */

	/**
	 * 统一处理异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ ServiceException.class, FileUploadException.class })
	public ResponseResult<Void> handleException(Throwable e) {
		ResponseResult<Void> rr = new ResponseResult<>();
		if (e instanceof UsernameDuplicateException) {
			// 400-用户名冲突
			rr.setState(400);
		} else if (e instanceof InsertException) {
			// 500-插入数据异常
			rr.setState(500);
		} else if (e instanceof UserNotFoundException) {
			// 401-用户名找不到
			rr.setState(401);
		} else if (e instanceof PasswordNotMatchException) {
			// 402密码输入错误
			rr.setState(402);
		} else if (e instanceof UpdateException) {
			// 501-更新数据异常
			rr.setState(501);
		} else if (e instanceof FileEmptyException) {
			// 600-文件是否为空
			rr.setState(600);
		} else if (e instanceof FileContentTypeException) {
			// 601-文件类型是否符合要求
			rr.setState(601);
		} else if (e instanceof FileSizeException) {
			// 602-文件大小是超过制定大小
			rr.setState(602);
		} else if (e instanceof FileIllegalStateException) {
			// 603-
			rr.setState(603);
		} else if (e instanceof FileIOException) {
			// 604-
			rr.setState(604);
		} else if (e instanceof AddressNotFoundException) {
			// 700-收货地址找不到异常
			rr.setState(700);
		} else if (e instanceof AccessDeniedException) {
			// 701-用户名uid和address中的uid不匹配异常
			rr.setState(701);
		} else if (e instanceof deleteException) {
			// 801-删除收货地址信息异常
			rr.setState(801);
		} else if (e instanceof CartNotFoundException) {
			// 901-购物车商品信息异常
			rr.setState(901);
		}
		rr.setMessage(e.getMessage());
		return rr;
	}
}
