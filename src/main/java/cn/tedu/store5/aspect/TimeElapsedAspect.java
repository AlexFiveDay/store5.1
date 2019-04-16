package cn.tedu.store5.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect //切面的编程注解
@Component //spring组件的注解
//@Component，@Contoller，@Service，@Repository
public class TimeElapsedAspect {
	//切面的方法名称可以自定义
	//切面方法必须添加参数ProceedingJoinPoint
	//参数对象调用proceed()方法相当于执行了切面对应的方法
	//@Around注解表示在切面对应的方法之前和之后都会执行某些代码
	@Around("execution(* cn.tedu.store5.service.impl.*.*(..))")
	public Object a(ProceedingJoinPoint pjp) throws Throwable {
		//记录开始时间
		long start = System.currentTimeMillis();
		//执行切面对应的方法
		Object result = pjp.proceed();
		//记录结束时间
		long end = System.currentTimeMillis();
		//计算得到耗时
		System.err.println("耗时："+(end-start));
		//返回切面方法的业务方法的返回值对象
		return result;
	}
}
