package com.wimoor.erp.api;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wimoor.api.amzon.inbound.pojo.dto.ShipInboundPlanDTO;
import com.wimoor.api.amzon.inbound.pojo.dto.ShipInboundShipmentDTO;
import com.wimoor.api.amzon.inbound.pojo.vo.ShipInboundShipmenSummarytVo;
import com.wimoor.common.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Component
@FeignClient(value = "wimoor-amazon")
public interface AmazonClientOneFeign {

	@GetMapping("/amazon/api/v1/shipForm/info/{shipmentid}")
	public Result<ShipInboundShipmenSummarytVo> infoAction(@PathVariable("shipmentid") String shipmentid);
	
	@GetMapping("/amazon/api/v1/shipForm/getShipment/{shipmentid}")
	public Result<ShipInboundShipmentDTO> getShipmentidAction(@PathVariable("shipmentid") String shipmentid);
	
	@PostMapping("/amazon/api/v1/shipForm/create")
	public Result<String> createShipmentAction(@ApiParam("货件信息")@RequestBody ShipInboundShipmentDTO dto);
	
	@PostMapping("/amazon/api/v1/shipForm/saveInboundPlan")
	public Result<?> saveInboundPlanAction(@ApiParam("发货计划")@RequestBody ShipInboundPlanDTO inplan);

    @PostMapping("/amazon/api/v1/shipForm/update")
	public Result<String> updateShipmentAction(@ApiParam("货件信息")@RequestBody ShipInboundShipmentDTO dto) ;
 

	@ApiOperation(value = "获取未同步货件")
	@GetMapping("/amazon/api/v1/shipFormSync/getUnSyncShipment")
	public Result<ShipInboundShipmenSummarytVo> getUnSyncShipmentAction(@RequestParam("groupid") String groupid, 
			                                                            @RequestParam("marketplaceid") String marketplaceid, 
			                                           	                @RequestParam("shipmentid") String shipmentid,
			                                                            @RequestParam("warehouseid") String warehouseid);
    @GetMapping("/amazon/api/v1/amzmarketplace/getByName")
    public Result<?> getMarketplaceAction(@RequestParam("name")String name) ;
    
	@GetMapping("/amazon/api/v1/shipFormSync/confirmSyncShipment")
	public Result<?> confirmSyncShipment(@RequestParam String shipmentid );
	
	@GetMapping("/amazon/api/v1/product/salesplan/getPlanModelItem")
	public Result<List<Map<String, Object>>> getPlanItem(@RequestParam String groupid);
}
