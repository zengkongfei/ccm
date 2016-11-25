package com.ccm.api.dao.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.SHA256Util;

public class RestPwd extends BaseDaoTestCase{
	public static final String ALGORITHM = "SHA-256";
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void test(){
		
//		List<B2BUser> list = userDao.getB2BUserByCompanyId("4");
//		for(B2BUser user:list){
//			String salt = DateUtil.getDateTime("yyyyMMddHHmmss", user.getCreatedTime());
//			String password = SHA256Util.SHA256Encrypt(user.getUsername()+"0512",salt);
//			String s = "update b2buser set password='"+password+"',passwordIsInit=0 where userid='"+user.getUserId()+"';";
//			System.out.println(s);
//		}
		
		
		//68 sha256密码
		String salt="20120424194320";
		
		try {
			Date date = DateUtil.convertStringToDate("yyyyMMddHHmmss", salt);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String password = SHA256Util.SHA256Encrypt("aaaaaa",salt);
		System.out.println(password);
		System.out.println(password.equalsIgnoreCase("9b8bb6d68ef1d7e608eabfb3c46af66325f7a6a52382b3af5d33a587ca65363e"));
		System.out.println(password.length());
		
//		reader();
		
		//68 admin
//		String salt="20120424194320";
//		String pass = "aaaaaa";
//		String password = passwordEncoder.encodePassword(pass, salt);
//		System.out.println(password);
	}
	
	
	/**
	 * 读取
	 */
	public void reader(){
		try {
			B2BUserCriteria userCriteria = new B2BUserCriteria();
			userCriteria.setPageSize(10000);
			userCriteria.setLanguage("zh_CN");
			B2BUserSearchResult result = userDao.searchUser(userCriteria);
			List<B2BUserVO> list = result.getResultList();
			
			String filePath="E:/work/ccm/Noname2.txt";
			String encoding="GBK";
			File file=new File(filePath);
			int i=0;
			if(file.isFile() && file.exists()){ //判断文件是否存在
			    InputStreamReader read = new InputStreamReader(
			    new FileInputStream(file),encoding);//考虑到编码格式
			    BufferedReader bufferedReader = new BufferedReader(read);
			    String lineTxt = null;
			    while((lineTxt = bufferedReader.readLine()) != null){
			    	String info = lineTxt.split("：")[1];
			    	String email = info.split("=")[0];
			    	String pwd = info.split("=")[1];
			    	String hotelid = info.split("=")[2];
			    	for(B2BUserVO user:list){
		    			String salt = DateUtil.getDateTime("yyyyMMddHHmmss", user.getCreatedTime());
		    			String password = passwordEncoder.encodePassword(pwd, salt);
		    			if(password.equalsIgnoreCase(user.getPassword())){
		    				System.out.println("username="+user.getUsername()+"pass="+pwd+" password="+password+" email="+email+" hotelid="+hotelid);
		    				i++;
		    				System.out.println(i);
		    			}
					}
			    }
			    read.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写出
	 */
	public void write(){
		
	}
	
}
