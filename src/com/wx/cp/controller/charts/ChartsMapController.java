package com.wx.cp.controller.charts;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import my.ann.Action;
import my.ann.Menu;

@Menu("地图测试")
@RequestMapping("/charts/map")
@Controller
public class ChartsMapController {
	
		@Action("地图测试")
		@RequestMapping("/list")
		public String sr(Model m) { 
			
			return "charts/map";
		}
}
