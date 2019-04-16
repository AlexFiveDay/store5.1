package cn.tedu.store5.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store5.controller.ex.FileContentTypeException;
import cn.tedu.store5.controller.ex.FileEmptyException;
import cn.tedu.store5.controller.ex.FileIOException;
import cn.tedu.store5.controller.ex.FileIllegalStateException;
import cn.tedu.store5.controller.ex.FileSizeException;
import cn.tedu.store5.entity.User;
import cn.tedu.store5.service.IUserService;
import cn.tedu.store5.util.ResponseResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	public static final  long uploadFileSize = 1*1024*1024;
	private static final String UPLOAD_DIR = "upload";
	public static List<String> uploadContentType;
	static {
		uploadContentType = new ArrayList<>();
		uploadContentType.add("image/jpeg");
		uploadContentType.add("image/png");
		uploadContentType.add("image/git");
	}
	@Autowired
	IUserService UserService;
	
	
	/**
	 * 注册业务
	 */
	@PostMapping("reg")
	public ResponseResult<Void> reg(User user) {
		System.err.println(user);
		UserService.reg(user);
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 登录业务
	 */
	//@RequestParam("username")页面必须传这个参数，否则报错
	@PostMapping("login")
	public ResponseResult<User> login(@RequestParam("username")String username,@RequestParam("password")String password,
			HttpSession session) {
		//执行登录验证
		User user = UserService.login(username, password);
		//向session中封装用户信息
		session.setAttribute("uid",user.getUid());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<>(SUCCESS,user);
	}
	/**
	 * 修改密码业务
	 */
	@PostMapping("change_password")
	public ResponseResult<Void> change_password(@RequestParam("oldPassword")String oldPassword,
			@RequestParam("newPassword")String newPassword,HttpSession session) {
		String username = session.getAttribute("username").toString();
		Integer uid = getUidFromSession(session);
		/*System.err.println(username);
		System.err.println(uid);
		System.err.println(oldPassword);
		System.err.println(newPassword);*/
		UserService.changePassword(uid, username, oldPassword, newPassword);
		return new ResponseResult<>(SUCCESS);
	}
	/**
	 * 更新用户信息业务
	 * @param user
	 * @return
	 */
	@PostMapping("change_info")
	public ResponseResult<Void> change_info(User user,HttpSession session) {
		//封装uid
		Integer uid = getUidFromSession(session);
		user.setUid(uid);
		UserService.changeInfo(user);
		return new ResponseResult<>(SUCCESS);
	}
	@PostMapping("showInfo")
	public ResponseResult<User> show_Info(HttpSession session) {
		//获取uid
		Integer uid = getUidFromSession(session);
		//获取用户数据
		User user = UserService.getByUid(uid);
		//返回用户数据
		return new ResponseResult<User>(SUCCESS, user);
	}
	@PostMapping("change_avatar")
	public ResponseResult<String> change_avatar(HttpServletRequest request,@RequestParam("file")MultipartFile file) {
		//需要一个常量定义传输文件的最大容量
		
		//判断文件是否为空，空抛出异常FileEmptyException
		if (file.isEmpty()) {
			throw new FileEmptyException("头像文件为空异常");
		}
		//判断文件大小是超过制定大小，超标抛出异常FileSizeException
		if (file.getSize()> uploadFileSize) {
			throw new FileSizeException("头像文件太大异常");
		}
		//判断文件类型是否符合要求，不符抛出异常FileContentTypeException
		if (!uploadContentType.contains(file.getContentType())) {
			throw new FileContentTypeException("头像文件类型不符异常");
		}
		//上传文件原本存在异常IllegalStateException, IOException,则自定义抛出FileIllegalStateException,FileIOException
		
		//获取原文件路径:parent:request.getServletContext().getRealPath("xxxx");
		//创建上传的文件夹:File parent = new File("xxxx");
		//文件夹是否存在exsits();文件夹不存在则创建parent.mkdirs()
		String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		//获取原文件的扩展名:  xxx.png
		//获取原文件的名字：file.getOriginalFilename();
		//截取最后一截:
		String originalFilename = file.getOriginalFilename();
		String prefix = System.nanoTime()+"";
		int beginindex = originalFilename.lastIndexOf(".");
		String filename = "";
		if (beginindex>0) {
			filename = originalFilename.substring(beginindex);
		}
		filename = prefix + filename;
		//获取avatar头像数据路径，需要先把头像数据保存
		//创建dest对象:File dest = new File(parent,filename);
		//执行保存:file.transferTo(dest)
		File dest = new File(parent,filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileIllegalStateException("头像文件非法状态异常");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileIOException("头像文件读取异常");
		}
		//获取uid:Integer uid = getUidFromSession(request.getSession());
		//或取avatar:/UPLOAD_DIR/文件名.扩展名
		Integer uid  = getUidFromSession(request.getSession());
		String avatar = "/"+UPLOAD_DIR+"/"+filename;
		//获取用户数据
		UserService.changeAvatar(uid, avatar);
		//返回用户数据
		return new ResponseResult<String>(SUCCESS, avatar);
	}
}
