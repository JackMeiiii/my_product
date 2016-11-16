package com.meihaifeng.service.aspect;import org.apache.commons.logging.Log;import org.apache.commons.logging.LogFactory;import org.aspectj.lang.JoinPoint;import org.aspectj.lang.ProceedingJoinPoint;import org.aspectj.lang.annotation.*;import org.springframework.stereotype.Component;/** * 浙江卓锐科技股份有限公司 * * @author meihf * @date 2016/11/14 * @description */@Component@Aspectpublic class AnnotionAopDemoUserLog {    private final static Log log = LogFactory.getLog("AnnotionAopDemoUserLog");    @Pointcut("execution(* com.meihaifeng.web.AopController.getUserLog3(..))")    public  void  aspect(){    }    @Before("aspect()")    public void before(JoinPoint point){        if (log.isInfoEnabled()){            log.info("before "+ point);        }    }    @After("aspect()")    public void after(JoinPoint point){        if (log.isInfoEnabled()){            log.info("after "+ point);        }    }    //配置后置返回通知,使用在方法aspect()上注册的切入点    @AfterReturning("aspect()")    public void afterReturn(JoinPoint joinPoint){        if(log.isInfoEnabled()){            log.info("afterReturn " + joinPoint);        }    }    //配置抛出异常后通知,使用在方法aspect()上注册的切入点    @AfterThrowing(pointcut="aspect()", throwing="ex")    public void afterThrow(JoinPoint joinPoint, Exception ex){        if(log.isInfoEnabled()){            log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());        }    }    //配置环绕通知,使用在方法aspect()上注册的切入点    @Around("aspect()")    public Object around(JoinPoint joinPoint){        long start = System.currentTimeMillis();        try {            ((ProceedingJoinPoint) joinPoint).proceed();            long end = System.currentTimeMillis();            if(log.isInfoEnabled()){                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");            }        } catch (Throwable e) {            long end = System.currentTimeMillis();            if(log.isInfoEnabled()){                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());            }        }        return null;    }}