package com.shaip.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.shaip.factories.DriverBase;
import com.shaip.factories.DriverFactoryShaip;

public class getHtmlCode {
	
	public String getBrowserInfo() throws Exception
	{
		String version=null;
		String browser = PropertiesOperationsShaip.getPropertyValueByKey("browser");
		DriverBase.initDrivers(browser);
		Capabilities cap = ((RemoteWebDriver) DriverFactoryShaip.getDriver()).getCapabilities();
	    String ver = cap.getBrowserVersion().toString();
	    version="v- "+ver;
		DriverBase.quitDriver();
	    return  version;
	}
	
	public String getHtml() throws Exception
	{
		
		String shaipLogo="\"https://images.g2crowd.com/uploads/product/image/large_detail/large_detail_130f08caf33cffb536b4ee4bdfe1a7e5/shaip-cloud.png\"";
		String jenkinsLogo="\"https://www.jenkins.io/images/logos/automotive/automotive.png\"";
		String browser= PropertiesOperationsShaip.getPropertyValueByKey("browser");
		String browserLink=null;
		if(browser.equalsIgnoreCase("ChromeHeadless") || browser.equalsIgnoreCase("Chrome"))
		{
			browserLink="\"https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png\"";
		}else if(browser.equalsIgnoreCase("firefoxHeadless")||browser.equalsIgnoreCase("firefox"))
		{
		
			browserLink="\"https://upload.wikimedia.org/wikipedia/commons/1/16/Firefox_logo%2C_2017.png\"";

		}
		String osName= System.getProperty("os.name");
		String osLink=null;
		if(osName.contains("Windows"))
		{
			osLink="\"https://www.freepnglogos.com/uploads/windows-logo-png/windows-logo-magnet-ief-update-telegram-eml-files-and-windows-21.png\"";
		}else if(osName.equalsIgnoreCase("Linux"))
		{
			
			osLink="\"https://upload.wikimedia.org/wikipedia/commons/1/16/Ubuntu_and_Ubuntu_Server_Icon.png\"";
			
		}
		
		
		String htmlCode="<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "	<tbody>\r\n"
				+ "		<tr>\r\n"
				+ "			<td align=\"center\">\r\n"
				+ "				<table class=\"col-600\" width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "					<tbody>\r\n"
				+ "						<tr>\r\n"
				+ "							<td align=\"center\" valign=\"top\" bgcolor=\"F0F0F0\">\r\n"
				+ "								<table class=\"col-600\" width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "									<tbody>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"40\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\" valign=\"top\" style=\"line-height: 0px;\"> <img style=\"display:block; line-height:0px; font-size:0px; border:0px;padding-bottom:25px \" src="+shaipLogo+" width=\"220\" height=\"110\" alt=\"logo\"> </td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\" style=\"font-family: 'Raleway', sans-serif; font-size:24PX; color:#2f2a95; line-height:24px; font-weight: bold; letter-spacing: 7px;\"> AUTOMATION <span style=\"font-family: 'Raleway', sans-serif; font-size:24px; color:#2f2a95; line-height:39px; font-weight: 300; letter-spacing: 6px;\">REPORT</span> </td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\" style=\"font-family: 'Raleway', sans-serif; font-size:14PX; color:#757575 ; line-height:24px; letter-spacing: 5px;padding-bottom:15px\"> SHAIP <span style=\"font-family: 'Raleway', sans-serif; font-size:14PX; color:#757575 ; line-height:39px; font-weight: 300; letter-spacing: 5px;padding-bottom:15px\">CLOUD2.0</span> </td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\" style=\"font-family: 'Lato', sans-serif; font-size:15px; color:#2a3a4b; line-height:24px; font-weight: 500\">Greetings..!!\r\n"
				+ "												<br>Hey Team! Your automation test execution job has been completed. </td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"20\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "									</tbody>\r\n"
				+ "								</table>\r\n"
				+ "							</td>\r\n"
				+ "						</tr>\r\n"
				+ "					</tbody>\r\n"
				+ "				</table>\r\n"
				+ "			</td>\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "			<td align=\"center\">\r\n"
				+ "				<table class=\"col-600\" width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-left:20px; margin-right:20px; border-left: 1px solid #dbd9d9; border-right: 1px solid #dbd9d9;\">\r\n"
				+ "					<tbody>\r\n"
				+ "						<tr>\r\n"
				+ "							<td height=\"35\"></td>\r\n"
				+ "						</tr>\r\n"
				+ "						<tr>\r\n"
				+ "							<td align=\"center\" style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">Test Automation Status</td>\r\n"
				+ "						</tr>\r\n"
				+ "						<tr>\r\n"
				+ "							<td height=\"10\"></td>\r\n"
				+ "						</tr>\r\n"
				+ "						<tr>\r\n"
				+ "							<td align=\"center\" style=\"font-family: 'Lato', sans-serif; font-size:13px; color:#757575; line-height:24px; font-weight: 300;padding-bottom:25px\"> Please find the attached report to know about automation test suit result for Shape Cloud 2.0. <br>Kindly let me know for any concern.</br> </td>\r\n"
				+ "						</tr>\r\n"
				+ "						<tr>\r\n"
				+ "							<td align=\"center\" style=\"font-family: 'Lato', sans-serif; font-size:16px; font-weight: bold; color:#2a3a4b;padding-bottom:5px\"> System/Environment Details: </td>\r\n"
				+ "						</tr>\r\n"
				+ "					</tbody>\r\n"
				+ "				</table>\r\n"
				+ "			</td>\r\n"
				+ "		</tr>\r\n"
				+ "		<!-- Dynami code -->\r\n"
				+ "		<tr>\r\n"
				+ "			<td align=\"center\">\r\n"
				+ "				<table class=\"col-600\" width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-left: 1px solid #dbd9d9; border-right: 1px solid #dbd9d9;margin-left:20px;margin-right:20px;padding-bottom:10px \">\r\n"
				+ "					<tbody>\r\n"
				+ "						<tr>\r\n"
				+ "							<td height=\"10\"></td>\r\n"
				+ "						</tr>\r\n"
				+ "						<tr>\r\n"
				+ "							<td>\r\n"
				+ "								<table class=\"col3\" width=\"183\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "									<tbody>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"30\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\">\r\n"
				+ "												<table class=\"insider\" width=\"133\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "													<tbody>\r\n"
				+ "														<tr align=\"center\" style=\"line-height:0px;\">\r\n"
				+ "															<td> <img style=\"display:block; line-height:0px; font-size:0px; border:0px;\" src="+browserLink+ "width=\"68\" height=\"68\" alt=\"icon\"> </td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr>\r\n"
				+ "															<td height=\"15\"></td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr align=\"center\">\r\n"
				+ "															<td style=\"font-family: 'Raleway', Arial, sans-serif; font-size:20px; color:#2b3c4d; line-height:24px; font-weight: bold;\">Browser</td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr>\r\n"
				+ "															<td height=\"10\"></td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr align=\"center\">\r\n"
				+ "															<td style=\"font-family: 'Lato', sans-serif; font-size:14px; color:#757575; line-height:24px; font-weight: 300;\">"+getBrowserInfo()+"</td>\r\n"
				+ "														</tr>\r\n"
				+ "													</tbody>\r\n"
				+ "												</table>\r\n"
				+ "											</td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"30\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "									</tbody>\r\n"
				+ "								</table>\r\n"
				+ "								<table width=\"1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\">\r\n"
				+ "									<tbody>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"20\" style=\"font-size: 0;line-height: 0;border-collapse: collapse;\">\r\n"
				+ "												<p style=\"padding-left: 24px;\">&nbsp;</p>\r\n"
				+ "											</td>\r\n"
				+ "										</tr>\r\n"
				+ "									</tbody>\r\n"
				+ "								</table>\r\n"
				+ "								<table class=\"col3\" width=\"183\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "									<tbody>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"30\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\">\r\n"
				+ "												<table class=\"insider\" width=\"133\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "													<tbody>\r\n"
				+ "														<tr align=\"center\" style=\"line-height:0px;\">\r\n"
				+ "															<td> <img style=\"display:block; line-height:0px; font-size:0px; border:0px;\" src="+osLink+ " width=\"68\" height=\"68\" alt=\"icon\"> </td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr>\r\n"
				+ "															<td height=\"15\"></td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr align=\"center\">\r\n"
				+ "															<td style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2b3c4d; line-height:24px; font-weight: bold;\">OS</td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr>\r\n"
				+ "															<td height=\"10\"></td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr align=\"center\">\r\n"
				+ "															<td style=\"font-family: 'Lato', sans-serif; font-size:14px; color:#757575; line-height:24px; font-weight: 300;\">"+osName+" </td>\r\n"
				+ "														</tr>\r\n"
				+ "													</tbody>\r\n"
				+ "												</table>\r\n"
				+ "											</td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"30\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "									</tbody>\r\n"
				+ "								</table>\r\n"
				+ "								<table width=\"1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\">\r\n"
				+ "									<tbody>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"20\" style=\"font-size: 0;line-height: 0;border-collapse: collapse;\">\r\n"
				+ "												<p style=\"padding-left: 24px;\">&nbsp;</p>\r\n"
				+ "											</td>\r\n"
				+ "										</tr>\r\n"
				+ "									</tbody>\r\n"
				+ "								</table>\r\n"
				+ "								<table class=\"col3\" width=\"183\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "									<tbody>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"30\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td align=\"center\">\r\n"
				+ "												<table class=\"insider\" width=\"133\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "													<tbody>\r\n"
				+ "														<tr align=\"center\" style=\"line-height:0px;\">\r\n"
				+ "															<td> <img style=\"display:block; line-height:0px; font-size:0px; border:0px;\" src="+jenkinsLogo+" width=\"68\" height=\"68\" alt=\"icon\"> </td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr>\r\n"
				+ "															<td height=\"15\"></td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr align=\"center\">\r\n"
				+ "															<td style=\"font-family: 'Raleway',  sans-serif; font-size:20px; color:#2b3c4d; line-height:24px; font-weight: bold;\">CI-CD</td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr>\r\n"
				+ "															<td height=\"10\"></td>\r\n"
				+ "														</tr>\r\n"
				+ "														<tr align=\"center\">\r\n"
				+ "															<td style=\"font-family: 'Lato', sans-serif; font-size:14px; color:#757575; line-height:24px; font-weight: 300;\">Jenkins 2.277.1</td>\r\n"
				+ "														</tr>\r\n"
				+ "													</tbody>\r\n"
				+ "												</table>\r\n"
				+ "											</td>\r\n"
				+ "										</tr>\r\n"
				+ "										<tr>\r\n"
				+ "											<td height=\"30\"></td>\r\n"
				+ "										</tr>\r\n"
				+ "									</tbody>\r\n"
				+ "								</table>\r\n"
				+ "							</td>\r\n"
				+ "						</tr>\r\n"
				+ "					</tbody>\r\n"
				+ "				</table>\r\n"
				+ "			</td>\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "			<td style=\"\">\r\n"
				+ "				<table class=\"col-600\" width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-left: 1px solid #dbd9d9; border-right: 1px solid #dbd9d9; border-bottom: 1px solid #dbd9d9;background-color:#F0F0F0\">\r\n"
				+ "					<tr align=\"center\">\r\n"
				+ "						<td style=\"padding:15px\">\r\n"
				+ "							<p style=\"font-family: 'Raleway', Arial, sans-serif; font-size:15px; color:#2b3c4d; line-height:24px; font-weight:600;margin:0px\">CP Environment</p>\r\n"
				+ "							<p style=\"font-family: 'Lato', sans-serif; font-size:13px; font-weight: 300; color:#757575; margin:0px\">qaworkforce.shaip.com</p>\r\n"
				+ "						</td>\r\n"
				+ "						\r\n"
				+ "						<td style=\"padding:15px\">\r\n"
				+ "							<p style=\"font-family: 'Raleway', Arial, sans-serif; font-size:15px; color:#2b3c4d; line-height:24px; font-weight:600;margin:0px\">Executed By</p>\r\n"
				+ "							<p style=\"font-family: 'Lato', sans-serif; font-size:13px; font-weight: 300; color:#757575 ;margin:0px\">Bhavin Sangani (QA)</p>\r\n"
				+ "							\r\n"
				+ "							<td style=\"padding:15px\">\r\n"
				+ "							<p style=\"font-family: 'Raleway', Arial, sans-serif; font-size:15px; color:#2b3c4d; line-height:24px; font-weight:600;margin:0px\">EP Environment</p>\r\n"
				+ "							<p style=\"font-family: 'Lato', sans-serif; font-size:13px; font-weight: 300; color:#757575; margin:0px\">qaenterprise.shaip.com</p>\r\n"
				+ "						</td>\r\n"
				+ "						</td>\r\n"
				+ "					</tr>\r\n"
				+ "				</table>\r\n"
				+ "			</td>\r\n"
				+ "		</tr>\r\n"
				+ "	</tbody>\r\n"
				+ "</table>";
		
		return htmlCode;
		
	}

}
