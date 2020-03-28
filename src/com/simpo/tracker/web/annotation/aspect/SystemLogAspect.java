package com.simpo.tracker.web.annotation.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.simpo.tracker.web.annotation.SystemControllerLog;
import com.simpo.tracker.web.annotation.entity.Xtgl_Operate_Info;
import com.simpo.tracker.web.annotation.service.Xtgl_Operate_Service;
import com.simpo.tracker.web.user.entity.UserInfo;


/**  
 * 切点类   
 */    
@Aspect
@Component
public class SystemLogAspect {
	
	@Autowired
	private Xtgl_Operate_Service operateService; 


	//本地异常日志记录对象 
	private static Log logger = LogFactory.getLog(SystemLogAspect.class);
	
    //Controller层切点    
    @Pointcut("@annotation(com.simpo.tracker.web.annotation.SystemControllerLog)")    
    public  void controllerAspect() {
    } 
    
    
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */    
    @Before("controllerAspect()")    
     public  void doBefore(JoinPoint joinPoint) {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("USERINFO");
		try {
			// *========控制台输出=========*//
			//System.out.println("=====前置通知开始=====");
			//System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			//System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
			Xtgl_Operate_Info log = new Xtgl_Operate_Info();
			log.setDescription(getControllerMethodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setCzr(user==null?0:user.getId());
			log.setCzsj(new Date());
			// 保存数据库
			if (operateService == null) {
				logger.info("operateDao is null");
			} else {
				operateService.save(log);
			}
			//System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}" + e.getMessage());
		}
    } 
    
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     *//*    
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        UserInfo user = (UserInfo) session.getAttribute("USERINFO");
        String params = "";    
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				//System.out.println("joinPoint.getArgs()[i]" + joinPoint.getArgs()[i]);
				params += JSONUtils.valueToString(joinPoint.getArgs()[i]) + ";";
			}
		}
		try {
			 ========控制台输出========= 
			//System.out.println("=====异常通知开始=====");
			//System.out.println("异常代码:" + e.getClass().getName());
			//System.out.println("异常信息:" + e.getMessage());
			//System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			//System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
			//System.out.println("请求参数:" + params);
			 ==========数据库日志========= 
			Xtgl_Operate_Info log = new Xtgl_Operate_Info();
			log.setDescription(getServiceMthodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setType("1");
			log.setExceptiondetail("");
			log.setExceptioncode("");
			log.setParams(params);
			log.setCzr(user==null?"":user.getId());
			log.setSsdw(user==null?0:user.getSsdw());
			log.setSsbm(user==null?0:user.getSsbm());
			log.setCzsj(DateTools.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			// 保存数据库
			if (operateService == null) {
				logger.info("operateDao is null");
			} else {
				operateService.save(log);
			}
			//System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}" + ex.getMessage());
		}
         ==========记录本地异常日志==========    
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}"+joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName()+e.getClass().getName()+e.getMessage()+params);
    }*/
    
    /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     /*public  static String getServiceMthodDescription(JoinPoint joinPoint)    
             throws Exception {     
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemServiceLog.class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }*/ 
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemControllerLog.class).description();    
                    break;    
                }    
            }    
        }    
         return description;    
    }  
}
