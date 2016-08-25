package edu.comp9321.ass2;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class ControlServlet
 */
@WebServlet(urlPatterns="/control",displayName="ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String url = null;
	String user = null;
	String password = null;
	boolean check=false;
	public void MySQLConnection (String sql,String typ) {
		  try {
		   Class.forName("com.mysql.jdbc.Driver"); 
		  } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
		  try {
		   url = 
		    "jdbc:mysql://localhost:3306/mycomp9321";
		   user = "root";
		   password = "87563518";
		   conn = DriverManager.getConnection(url,user,password);
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		  try {
		   stmt = conn.createStatement();
		  if (typ.equals("query")) {
			  rs = stmt.executeQuery(sql);
		  }
		  else {
			  stmt.executeUpdate(sql);
		  }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
	}
	public void CloseSQL () {
		 try {
			   if(rs != null) {
			    rs.close();
			    rs = null;
			   }
			   if(stmt != null) {
			    stmt.close();
			    stmt = null;
			   }
			   if(conn != null) {
			    conn.close();
			    conn = null;
			   }
			  } catch(Exception e) {
			   e.printStackTrace();
			  }
	}
	private static final String CONTENT_TYPE = "text/hml; charset=UTF-8";
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
		 String an = request.getParameter("an");
		 String activen = request.getParameter("activen");
		 String logname = request.getParameter("logname");
		 String logpass = request.getParameter("logpass");
		 String item_list = request.getParameter("item_list");
		 String activename= request.getParameter("username");
		 String posttype= request.getParameter("posttype");
		 String search = request.getParameter("search");
		 String searchtype = request.getParameter("searchtype");
		 String asearch = request.getParameter("asearch");
		 String type= request.getParameter("type");
		 String nextpage = request.getParameter("nextpage");
		 String checku = request.getParameter("checkuser");
		 String mail = request.getParameter("mail");
		 String reg = request.getParameter("reg");
		 String lout = request.getParameter("logout");
		 //System.out.println(" wocao ");
		 JourneyBean jn = (JourneyBean) request.getSession().getAttribute("journey");
		 //ServletContext servletContext = request.getSession().getServletContext();
		 //String relativeWebPath = "upload/";
		 //String nextPage = nextpage;
		 //System.out.println(activename+" wocao " + type);
		 if (lout != null) {
			 //System.out.println("cheng gong!");
			 jn.setUser_name(null);
			 jn.setUser_id(null);
			 response.sendRedirect("/BookStore/index.jsp");
		 }
		 if (logname!=null && logpass!=null) {
			 //RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 //rd.forward(request, response);
			 String sql="select * from user where username='"+logname+"'";
			 MySQLConnection(sql,"query");
			 try {
				if (rs.next()) {
					String typeid=rs.getString("typeid");
					String pass=rs.getString("password");
					if (pass.equals(logpass)) {
						if (typeid.equals("2") || typeid.equals("1")) {
							String username=rs.getString("username");
							String userid=rs.getString("userid");
							jn.setUser_name(username);
							jn.setUser_id(userid);
							jn.setError(null);
						}
						else {
							jn.setError("notavailable");
						}
					}
					else {
						jn.setError("wrongpass");
					}
				 }
				else {
					jn.setError("nouser");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 CloseSQL();
			 response.sendRedirect(request.getHeader("Referer"));
		 }
		 if (action !=null) {
			 response.sendRedirect("/BookStore/" + nextpage);
		 }
		 if (checku != null) {
			 response.setContentType(CONTENT_TYPE);
			 String msg = "";
			 String sql = "select username from user where username='"+checku+"'";
			 MySQLConnection(sql,"query");
			 try {
				if (rs.next()) {
				 check=true;
				 }
				 else {
				 check=false;
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 CloseSQL();
			 //System.out.println(checku);
			 if (check == true) {
				 msg = "Already exist!";
				 PrintWriter out = response.getWriter();  
				 out.println(msg);
				 out.flush();
				 out.close();
			 }
		 }
		 if (reg != null) {
			 String username = request.getParameter("username");
			 String passwd = request.getParameter("passwd");
			 String nickname = request.getParameter("nickname");
			 String firstname = request.getParameter("firstname");
			 String lastname = request.getParameter("lastname");
			 String email = request.getParameter("mail");
			 String phone = request.getParameter("phone");
			 String birthday = request.getParameter("birthday");
			 String address = request.getParameter("address");
			 String credit = request.getParameter("credit");
			 String sql = "INSERT INTO `user` (`username`, `nickname`, `firstname`, `lastname`, `email`, `phone`, `birthdate`, `typeid`, `password`, `address`, `creditnum`) VALUES "
				 		+ "('"+username+"', '"+nickname+"', '"+firstname+"', '"+lastname+"', '"+email+"', '"+phone+"', '"+birthday+"', '4', '"+passwd+"','"+address+"','"+credit+"');";
			 MySQLConnection(sql,"123");
			 CloseSQL();
			 String sql2 = "select * from user where username='"+username+"'";
			 MySQLConnection(sql2,"query");
			 try {
					if (rs.next()) {
						String sql3 = "insert into `cart` (`buyerid`) values ('"+rs.getString("userid")+"')";
						CloseSQL();
						MySQLConnection(sql3,"123");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 CloseSQL();
			 sendMail(mail,activename,an);
			 response.sendRedirect("/BookStore/"+nextpage);
		 }
		 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 if (isMultipart == true) {
			 		String itemname = new String();
     			 	String author = new String();
     			 	String editor = new String();
     			 	String title = new String();
     			 	String volume = new String();
     			 	String pubtype = new String();
     			 	String itemtype = new String();
     			 	String venue = new String();
     			 	String uid = new String();
	            try {
	                FileItemFactory factory = new DiskFileItemFactory();
	                ServletFileUpload upload = new ServletFileUpload(factory);
	                
	                List<FileItem> fileItems = upload.parseRequest(request);
	                Iterator<FileItem> iter = fileItems.iterator();
	                
	                // 依次处理每个表单域
	                while (iter.hasNext()) {
	                    FileItem item = (FileItem) iter.next();
	                    
	                    if(item.isFormField()){
	                        // 如果item是正常的表单域
	                        String name = item.getFieldName();
	                        String value = item.getString();
//	                        System.out.println(""+name+"：" + value);
	                        if (name.equals("itemname")) {
	                        	//System.out.println("itemname correct!");
	                        	itemname = value;
	                        }
	                        else if (name.equals("author")) {
	                        	author = value;
	                        }
	                        else if (name.equals("editor")) {
	                        	editor = value;
	                        }
	                        else if (name.equals("title")) {
	                        	title = value;
	                        }
	                        else if (name.equals("volume")) {
	                        	volume = value;
	                        }
	                        else if (name.equals("pubtype")) {
	                        	pubtype = value;
	                        }
	                        else if (name.equals("itemtype")) {
	                        	itemtype = value;
	                        }
	                        else if (name.equals("venue")) {
	                        	venue = value;
	                        }
	                        else if (name.equals("uid")) {
	                        	uid = value;
	                        }
	                        //System.out.print("表单域名为:"+name+"表单域值为:"+value);
	                    }	
	                    else{
	                        // 如果item是文件上传表单域
	                        
	                        // 获得文件名及路径
	                        String fileName = item.getName();
	                        if (fileName != null) {
	                            File fullFile = new File(item.getName());     
	                            // 如果文件存在则上传
	                            if(fullFile.exists()){
	                   			 	//System.out.println("itemname：" + itemname);
	                            	IpTimeStamp its=new IpTimeStamp(InetAddress.getLocalHost().getHostAddress());
	                            	String ext = fullFile.getName().substring(fullFile.getName().lastIndexOf(".")+1);
	                       		 	String absoluteDiskPath = this.getServletContext().getRealPath("/upload/");
	                       		 	//System.out.println("上传路径为：" + absoluteDiskPath);
	                       		 	String uploadname = its.getIpTimeRand()+ "." + ext;
	                                File fileOnServer = new File(absoluteDiskPath,uploadname);
	                                item.write(fileOnServer);
	                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	                                String dt = df.format(new Date());
	                                //System.out.println("文件"+fileOnServer.getName()+"上传成功");
	                                String sql = "INSERT INTO item (`sellerid`,`itemname`, `author`, `editor`, `title`, `volume`, `pubtype`, `itemtype`, `url`, `onlinedate`,`venue`,`status`) VALUES "
		             				 		+ "('"+uid+"','"+itemname+"', '"+author+"', '"+editor+"', '"+title+"', '"+volume+"', '"+pubtype+"', '"+itemtype+"', '"+uploadname+"', '"+dt+"','"+venue+"','1')";
		                   			MySQLConnection(sql,"123");
		                   			CloseSQL();
	                            }
	                        }
	                    }
	                }
	   			 response.sendRedirect("myitem.jsp?userid="+jn.getUser_id());             
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

		if (activename != null && type != null && activen != null) {
			String sql="UPDATE `user` SET `typeid`='"+activen+"' WHERE `username`='"+activename+"';";
			MySQLConnection(sql,"123");
			CloseSQL();
			response.sendRedirect("/BookStore/index.jsp");
		}
		if (item_list != null ) {
			if (item_list.equals("1")) {
				String sql = "select * from item where status='1'";
				MySQLConnection(sql,"query");
				Vector<String> name= new Vector<String>();
				Vector<String> id= new Vector<String>();
				Vector<String> u= new Vector<String>();
				try {
					while (rs.next()) {
						String itemname=rs.getString("itemname");
						name.add(itemname);
						String itemid=rs.getString("itemid");
						id.add(itemid);
						String url=rs.getString("url");
						u.add(url);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jn.setItem_name(name);
				jn.setItem_id(id);
				jn.setUrl(u);
				CloseSQL();
				String sql1 = "select * from itemtype";
				MySQLConnection(sql1,"query");
				Vector<String> name1= new Vector<String>();
				try {
					while (rs.next()) {
						String listname=rs.getString("listname");
						name1.add(listname);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jn.setList_name(name1);
				CloseSQL();
				response.sendRedirect("/BookStore/index.jsp");
			}
			else if (item_list.equals("2")) {
				if (search != null) {
				String sql1 = "select * from item where concat(itemname,author,editor,title,volume,pubtype,venue,onlinedate,itemtype) like '%"+search+"%' and status='1'";
				MySQLConnection(sql1,"query");
				//System.out.println(sql1);
				Vector<String> name1= new Vector<String>();
				Vector<String> id1= new Vector<String>();
				Vector<String> u= new Vector<String>();
				try {
					if (rs != null){
						while (rs.next()) {
							String itemname=rs.getString("itemname");
							name1.add(itemname);

							String itemid=rs.getString("itemid");
							id1.add(itemid);

							String url=rs.getString("url");
							u.add(url);
						}
					}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jn.setItem_name(name1);
				jn.setItem_id(id1);
				jn.setUrl(u);
				CloseSQL();
				}
				else {
					String sql1 = "select * from item where "+searchtype+" like '%"+asearch+"%' and status='1'";
					MySQLConnection(sql1,"query");
					//System.out.println(sql1);
					Vector<String> name1= new Vector<String>();
					Vector<String> id1= new Vector<String>();
					Vector<String> u= new Vector<String>();
					try {
						if (rs != null){
							while (rs.next()) {
								String itemname=rs.getString("itemname");
								name1.add(itemname);

								String itemid=rs.getString("itemid");
								id1.add(itemid);

								String url=rs.getString("url");
								u.add(url);
							}
						}					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jn.setItem_name(name1);
					jn.setItem_id(id1);
					jn.setUrl(u);
					CloseSQL();
				}
			}
			else {
				String sql1 = "select * from item where itemtype='"+posttype+"' and status='1'";
				MySQLConnection(sql1,"query");
				//System.out.println(posttype);
				Vector<String> name1= new Vector<String>();
				Vector<String> id1= new Vector<String>();
				Vector<String> u= new Vector<String>();
				try {
					if (rs != null){
						while (rs.next()) {
							String itemname=rs.getString("itemname");
							name1.add(itemname);
							String itemid=rs.getString("itemid");
							id1.add(itemid);
							String url=rs.getString("url");
							u.add(url);
						}
					}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jn.setItem_name(name1);
				jn.setItem_id(id1);
				jn.setUrl(u);
				CloseSQL();
			}
		}
		}
	public void sendMail (String mail,String activename,String ac) {
		String activenum = new String();
		if (ac.equals("Buyer")) {
			activenum = "1";
		}
		else {
			activenum = "2";
		}
	      String to = mail;
	      String from = "zhuweic@hotmail.com";
	      String host = "smtp-mail.outlook.com";
	      Properties properties = System.getProperties();
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.user", from);
	      properties.put("mail.smtp.password", "7758521a");
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.smtp.auth", "true");
	      Session session = Session.getDefaultInstance(properties);
	      try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         message.setSubject("This is the Subject Line!");
	         message.setContent("<h1>This is an official e-mail! Please click the link below to active your account!"
	         		+ "</h1><h3><a href='http://localhost:8080/BookStore/control?"
	         		+ "username="+activename+"&type=2&activen="+activenum+"'>Click to active!</a></h3>"
	         		,"text/html;charset=UTF-8");
	         Transport transport = session.getTransport("smtp");
	         transport.connect(host, from, "7758521a");
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
