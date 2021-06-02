//package com.yaoshun;
//
//
//
//import com.yaoshun.web.bean.User;
//import com.yaoshun.web.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import java.util.List;
//
///***
// * 受保护的资源服务
// *
// */
//
//public class ResourceController {
//
//    @Autowired
//    IUserService userService;
//
//    /**
//     * 需要用户角色权限
//     *
//     * @return
//     */
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @RequestMapping(value = "user", method = RequestMethod.GET)
//    public String helloUser() {
//        return "hello user";
//    }
//
//    /**
//     * 需要管理角色权限
//     *
//     * @return
//     */
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping(value = "admin", method = RequestMethod.GET)
//    public String helloAdmin() {
//        return "hello admin";
//    }
//
//    /**
//     * 需要客户端权限
//     *
//     * @return
//     */
//    @PreAuthorize("hasRole('ROLE_CLIENT')")
//    @RequestMapping(value = "client", method = RequestMethod.GET)
//    public String helloClient() {
//        return "hello user authenticated by normal client";
//    }
//
//    /**
//     * 需要受信任的客户端权限
//     *
//     * @return
//     */
//    @PreAuthorize("hasRole('ROLE_TRUSTED_CLIENT')")
//    @RequestMapping(value = "trusted_client", method = RequestMethod.GET)
//    public String helloTrustedClient() {
//        return "hello user authenticated by trusted client";
//    }
//
//    /**
//     * 返回当前用户的信息
//     *
//     * @return
//     */
//    @RequestMapping(value = "principal", method = RequestMethod.GET)
//    public Object getPrincipal() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return principal;
//    }
//
//    /**
//     * 返回用户的用户名
//     *
//     * @return
//     */
//    @RequestMapping(value = "roles", method = RequestMethod.GET)
//    public Object getRoles() {
//        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//    }
//
//    @RequestMapping(value = "/sendmsg", method = RequestMethod.GET)
//    @ResponseBody
//    public Object sendMessage() throws MessagingException {
//        List<User> list = userService.list();
//        return list;
//    }
//
//
//
//
//
////    @RequestMapping(value = "/sendmsg", method = RequestMethod.GET)
////    @ResponseBody
////    public String sendMessage() throws MessagingException {
////        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
////        // 设定mail server
////        senderImpl.setHost("smtp.qq.com");
////        senderImpl.setUsername("978728379@qq.com"); // 根据自己的情况,设置username
////        senderImpl.setPassword("hwkhhbvpjlcebbjd"); // 根据自己的情况, 设置password，注意qq邮箱的话此处设置16位授权码，不是邮箱密码，切记！
////        //加认证机制
////        Properties javaMailProperties = new Properties();
////        javaMailProperties.put("mail.smtp.auth", true);
////        //javaMailProperties.put("mail.smtp.starttls.enable", true);
////        javaMailProperties.put("mail.smtp.timeout", 5000);
////        senderImpl.setJavaMailProperties(javaMailProperties);
////        senderImpl.testConnection();
////
////        // 建立邮件消息
////        SimpleMailMessage mailMessage = new SimpleMailMessage();
////        // 设置收件人，寄件人 用数组发送多个邮件
////        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
////        // mailMessage.setTo(array);
////        mailMessage.setTo("978728379@qq.com");
////        mailMessage.setFrom("978728379@qq.com");
////        mailMessage.setSubject(" 测试简单文本邮件发送！ ");
////        mailMessage.setText(" 测试我的简单邮件发送机制！！ ");
////        // 发送邮件
////        senderImpl.send(mailMessage);
////        System.out.println(" 邮件发送成功.. ");
////        return "haHa";
////
////    }
//}