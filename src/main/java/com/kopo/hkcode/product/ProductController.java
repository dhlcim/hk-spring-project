package com.kopo.hkcode.product;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // Node.js 서버 포트 허용
public class ProductController {
	@Autowired
	private ProductService productService;

	// 기존 GET 방식 최대 QTY 조회 (JSP 반환)
	@GetMapping(value = "/getMaxQty")
	public String getMaxQty(@RequestParam("regionId") String regionId, Model model) {
		String result = productService.getMaxQtyByRegion(regionId);
		model.addAttribute("msg", result);
		return "productResult";
	}

	// 새로 추가: POST 방식 최대 QTY 조회 (JSON 반환) - Node.js 프론트엔드 연동용
	@PostMapping(value = "/getMaxQty")
	@ResponseBody
	public Map<String, Object> getMaxQtyPost(@RequestParam("regionid") String regionid,
			@RequestParam("productgroup") String productgroup) {
		Map<String, Object> response = new HashMap<>();
		try {
			// 고정값 대신 실제 DB 조회 서비스 호출
			String result = productService.getMaxQtyByRegion(regionid);
			response.put("regionId", regionid);
			response.put("productGroup", productgroup);
			response.put("message", result); // "지역 [A45]의 최대 판매량은 500개 입니다." 형식으로 반환
			response.put("status", "success");
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
		}
		return response;
	}

	// 기존 평균 QTY 조회 (JSP 반환)
	@GetMapping(value = "/getAvgQty")
	public String getAvgQty(@RequestParam("regionId") String regionId, Model model) {
		String result = productService.getAvgQtyByRegion(regionId);
		model.addAttribute("msg", result);
		return "avgResult";
	}
}