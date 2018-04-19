package com.drunck.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drunck.domain.SysAdmin;
import com.drunck.domain.SysPrivilage;
import com.drunck.service.SysAdminService;
import com.drunck.service.SysPrivilageService;
import com.drunck.utils.ImageUtil;

@Controller
@RequestMapping("sys")
public class IndexController extends BaseController {
	private static final String CAPTCHA = "captcha";
	@Resource
	private SysAdminService sysAdminService;
	@Resource
	private SysPrivilageService sysPrivilageService;

	@RequestMapping("loginfrom")
	public String loginfrom(String id) {
		return "login";
	}

	@RequestMapping("login")
	public String login(String id, ModelMap map) {
		SysAdmin sysAdmin = (SysAdmin) this.getSession().getAttribute("currentUser");
		List<SysPrivilage> menus = sysPrivilageService.queryMenu(sysAdmin.getId());
		map.put("menus", menus);
		return "index";
	}

	@RequestMapping(value = "/getCode")
	public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();
		Map<String, Object> map = ImageUtil.getImageCode(60, 20, os);
		request.getSession().setAttribute(IndexController.CAPTCHA, map.get("strEnsure").toString().toLowerCase());
		try {
			ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
		} catch (IOException e) {
			return "";
		}
		return null;
	}

}
