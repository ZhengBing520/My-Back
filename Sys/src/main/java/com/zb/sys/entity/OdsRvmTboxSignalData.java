package com.zb.sys.entity;

import com.zb.rdb.bean.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ods实时数据
 * </p>
 *
 * @author bzheng
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="OdsRvmTboxSignalData对象", description="ods实时数据")
public class OdsRvmTboxSignalData extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "vin")
    private String vin;

    @ApiModelProperty(value = "采集时间")
    private String collectdate;

    @ApiModelProperty(value = "车型")
    private String vehicletype;

    @ApiModelProperty(value = "gps时间")
    private String gnsstime;

    @ApiModelProperty(value = "经度")
    private String gnsslong;

    @ApiModelProperty(value = "纬度")
    private String gnsslat;

    @ApiModelProperty(value = "海拔")
    private String gnssalt;

    @ApiModelProperty(value = "方向")
    private String gnsshead;

    @ApiModelProperty(value = "卫星数")
    private String gnsssats;

    @ApiModelProperty(value = "水平精度因子")
    private String hdop;

    @ApiModelProperty(value = "gps 状态")
    private String gpsstatus;

    @ApiModelProperty(value = "速度")
    private String vehspeed;

    @ApiModelProperty(value = "发动机转速")
    private String vehrpm;

    @ApiModelProperty(value = "系统电源模式")
    private String vehsyspwrmod;

    @ApiModelProperty(value = "档位")
    private String vehgearpos;

    @ApiModelProperty(value = "加速度x")
    private String tboxaccelx;

    @ApiModelProperty(value = "加速度y")
    private String tboxaccely;

    @ApiModelProperty(value = "加速度z")
    private String tboxaccelz;

    @ApiModelProperty(value = "油门开合度")
    private String accelactupos;

    @ApiModelProperty(value = "刹车开合度")
    private String vehbrakepos;

    @ApiModelProperty(value = "方向盘角度")
    private String vehsteeringangle;

    @ApiModelProperty(value = "总油耗")
    private String vehfuelconsumed;

    @ApiModelProperty(value = "定速巡航标识")
    private String vehcruiseactive;

    @ApiModelProperty(value = "左前门窗状态")
    private String vehwindowfrontleft;

    @ApiModelProperty(value = "左后门窗状态")
    private String vehwindowrearleft;

    @ApiModelProperty(value = "右前门窗状态")
    private String vehwindowfrontright;

    @ApiModelProperty(value = "右后门窗状态")
    private String vehwindowrearright;

    @ApiModelProperty(value = "天窗状态")
    private String vehsunroof;

    @ApiModelProperty(value = "定速巡航标识")
    private String vehcruiseenabled;

    @ApiModelProperty(value = "定速巡航目标速度")
    private String vehcruisetargetspeed;

    @ApiModelProperty(value = "车外温度")
    private String vehoutsidetemp;

    @ApiModelProperty(value = "车内温度")
    private String vehinsidetemp;

    @ApiModelProperty(value = "空调开关")
    private String vehac;

    @ApiModelProperty(value = "空调自动档开关")
    private String vehacauto;

    @ApiModelProperty(value = "空调循环方向")
    private String vehaccircdirection;

    @ApiModelProperty(value = "空调循环模式")
    private String vehaccirctype;

    @ApiModelProperty(value = "风扇速度")
    private String vehacfanspeed;

    @ApiModelProperty(value = "主驾目标温度")
    private String vehacdrvtargettemp;

    @ApiModelProperty(value = "副驾目标温度")
    private String vehacpasstargettemp;

    @ApiModelProperty(value = "左转向灯")
    private String vehindlightleft;

    @ApiModelProperty(value = "右转向灯")
    private String vehindlightright;

    @ApiModelProperty(value = "雨刷档位")
    private String vehwiperswitchfront;

    @ApiModelProperty(value = "下雨检测")
    private String vehraindetected;

    @ApiModelProperty(value = "夜晚检测")
    private String vehnightdetected;

    @ApiModelProperty(value = "油箱液位")
    private String vehfuellev;

    @ApiModelProperty(value = "电瓶电压")
    private String vehbatt;

    @ApiModelProperty(value = "冷却液温度")
    private String vehcoolanttemp;

    @ApiModelProperty(value = "总里程")
    private String vehodo;

    @ApiModelProperty(value = "喇叭")
    private String vehhorn;

    @ApiModelProperty(value = "机油压力报警")
    private String vehoilpressurewarning;

    @ApiModelProperty(value = "发动机非排放相关故障")
    private String vehmilwarning;

    @ApiModelProperty(value = "发动机排放相关故障")
    private String vehdrivebywirewarning;

    @ApiModelProperty(value = "移动国家码")
    private String cellmcc;

    @ApiModelProperty(value = "移动网络码")
    private String cellmnc;

    @ApiModelProperty(value = "信号强度")
    private String cellsignalstrength;

    @ApiModelProperty(value = "网络制式")
    private String cellrat;

    @ApiModelProperty(value = "地区区域码")
    private String celllac;

    @ApiModelProperty(value = "基站代码")
    private String cellcellid;

    @ApiModelProperty(value = "网络频段")
    private String cellchanid;

    @ApiModelProperty(value = "工作模式")
    private String vehworkmodelG;

    @ApiModelProperty(value = "电动动力系统就绪")
    private String veheptrdy;

    @ApiModelProperty(value = "bms状态")
    private String vehbmsbscsta;

    @ApiModelProperty(value = "车速有效")
    private String vehspdavgdrvnv;

    @ApiModelProperty(value = "总里程有效")
    private String vehodov;

    @ApiModelProperty(value = "bms电压有效")
    private String vehbmspackvolv;

    @ApiModelProperty(value = "bms电压")
    private String vehbmspackvol;

    @ApiModelProperty(value = "bms电流有效")
    private String vehbmspackcrntv;

    @ApiModelProperty(value = "bms电流")
    private String vehbmspackcrnt;

    @ApiModelProperty(value = "bms电量有效")
    private String vehbmspacksocv;

    @ApiModelProperty(value = "bms电量")
    private String vehbmspacksoc;

    @ApiModelProperty(value = "高压dcdc状态")
    private String vehhvdcdcsta;

    @ApiModelProperty(value = "电动动力传动系统输入轴扭矩")
    private String vehepttrinptshafttoq;

    @ApiModelProperty(value = "电动动力传动系统输入轴扭矩有效")
    private String vehepttrinptshafttoqv;

    @ApiModelProperty(value = "制动踏板离散输入状态")
    private String veheptbrkpdldscrtinptsts;

    @ApiModelProperty(value = "制动系统制动灯要求")
    private String vehbrksysbrklghtsreqd;

    @ApiModelProperty(value = "电动停车制动系统要求制动灯")
    private String vehepbsysbrklghtsreqd;

    @ApiModelProperty(value = "挡位有效")
    private String vehtrshftlvrposv;

    @ApiModelProperty(value = "bms隔离电阻值")
    private String vehbmsptisltnrstc;

    @ApiModelProperty(value = "bms隔离电阻值有效")
    private String vehbmsptisltnrstcv;

    @ApiModelProperty(value = "加速踏板有效")
    private String veheptaccelactuposv;

    @ApiModelProperty(value = "制动踏板离散输入状态有效")
    private String veheptbrkpdldscrtinptstsv;

    @ApiModelProperty(value = "tm电机逆变电流有效")
    private String vehtminvtrcrntv;

    @ApiModelProperty(value = "tm电机逆变电流")
    private String vehtminvtrcrnt;

    @ApiModelProperty(value = "tm电机状态")
    private String vehtmsta;

    @ApiModelProperty(value = "tm电机逆变温度")
    private String vehtminvtrtem;

    @ApiModelProperty(value = "tm电机转速有效")
    private String vehtmspdv;

    @ApiModelProperty(value = "tm电机转速")
    private String vehtmspd;

    @ApiModelProperty(value = "tm电机实际转矩有效")
    private String vehtmactutoqv;

    @ApiModelProperty(value = "tm电机实际转矩")
    private String vehtmactutoq;

    @ApiModelProperty(value = "tm电机定子温度")
    private String vehtmsttrtem;

    @ApiModelProperty(value = "高压dcdc高压侧电压有效")
    private String vehhvdcdchvsidevolv;

    @ApiModelProperty(value = "高压dcdc高压侧电压")
    private String vehhvdcdchvsidevol;

    @ApiModelProperty(value = "isg电机逆变电流有效")
    private String vehisginvtrcrntv;

    @ApiModelProperty(value = "isg电机逆变电流")
    private String vehisginvtrcrnt;

    @ApiModelProperty(value = "isg电机状态")
    private String vehisgsta;

    @ApiModelProperty(value = "isg电机逆变温度")
    private String vehisginvtrtem;

    @ApiModelProperty(value = "isg电机转速有效")
    private String vehisgspdv;

    @ApiModelProperty(value = "isg电机转速")
    private String vehisgspd;

    @ApiModelProperty(value = "isg电机实际转矩有效")
    private String vehisgactutoqv;

    @ApiModelProperty(value = "isg电机实际转矩")
    private String vehisgactutoq;

    @ApiModelProperty(value = "isg电机定子温度")
    private String vehisgsttrtem;

    @ApiModelProperty(value = "发动机转速状态")
    private String vehenspdsts;

    @ApiModelProperty(value = "燃料消耗率")
    private String vehavgfuelcsumpG;

    @ApiModelProperty(value = "bms单体最高电压序号")
    private String vehbmscellmaxvolindx;

    @ApiModelProperty(value = "bms单体最高电压")
    private String vehbmscellmaxvol;

    @ApiModelProperty(value = "bms单体最高电压有效")
    private String vehbmscellmaxvolv;

    @ApiModelProperty(value = "bms单体最低电压序号")
    private String vehbmscellminvolindx;

    @ApiModelProperty(value = "bms单体最低电压")
    private String vehbmscellminvol;

    @ApiModelProperty(value = "bms单体最低电压有效")
    private String vehbmscellminvolv;

    @ApiModelProperty(value = "bms单体最高温度序号")
    private String vehbmscellmaxtemindx;

    @ApiModelProperty(value = "bms单体最高温度")
    private String vehbmscellmaxtem;

    @ApiModelProperty(value = "bms单体最高温度有效")
    private String vehbmscellmaxtemv;

    @ApiModelProperty(value = "bms单体最低温度序号")
    private String vehbmscellmintemindx;

    @ApiModelProperty(value = "bms单体最低温度")
    private String vehbmscellmintem;

    @ApiModelProperty(value = "bms单体最低温度有效")
    private String vehbmscellmintemv;

    @ApiModelProperty(value = "高压dcdc温度")
    private String vehhvdcdctem;

    @ApiModelProperty(value = "制动液液位低")
    private String vehbrkfludlvllow;

    @ApiModelProperty(value = "红色刹车灯指示")
    private String vehbrksysredbrktlltreq;

    @ApiModelProperty(value = "防抱死制动系统失败")
    private String vehabsf;

    @ApiModelProperty(value = "车辆稳定性增强状态")
    private String vehvsests;

    @ApiModelProperty(value = "电动机械助力器报警")
    private String vehibstrwrnngio;

    @ApiModelProperty(value = "bms高压联锁回路闭合")
    private String vehbmshvilclsd;

    @ApiModelProperty(value = "混动模式（大数据rvm）")
    private String vehelecvehsysmd;

    @ApiModelProperty(value = "发动机油喷指令（大数据rvm）")
    private String veheptenfuelpumponreq;

    @ApiModelProperty(value = "充电器高压电压输出（大数据rvm）")
    private String vehchargerhvvolt;

    @ApiModelProperty(value = "充电器高压电流输出（大数据rvm）")
    private String vehchargerhvcurrent;

    @ApiModelProperty(value = "单体电池电压列表")
    private String vehbmscellvolt;

    @ApiModelProperty(value = "探针温度列表")
    private String vehbmscelltem;

    @ApiModelProperty(value = "可充电储能装置故障代码总数")
    private String bmstotaldtc;

    @ApiModelProperty(value = "可充电储能装置故障代码列表")
    private String dtcinfomationbms;

    @ApiModelProperty(value = "发动机故障代码总数")
    private String ecmtotaldtc;

    @ApiModelProperty(value = "发动机故障代码列表")
    private String dtcinfomationecm;

    @ApiModelProperty(value = "tm电机故障码总数")
    private String tmtotaldtc;

    @ApiModelProperty(value = "tm电机故障码列表")
    private String dtcinfomationtc;

    @ApiModelProperty(value = "isg电机故障码总数")
    private String isgtotaldtc;

    @ApiModelProperty(value = "isg电机故障码列表")
    private String dtcinfomationisc;

    @ApiModelProperty(value = "电气传动系高压联锁回路闭合状态")
    private String vehepthvilclsdsts;

    @ApiModelProperty(value = "hvdcdc在线信号")
    private String vehhvdcdcavlbly;

    @ApiModelProperty(value = "tm电机逆变电压有效")
    private String vehtminvtrvolv;

    @ApiModelProperty(value = "tm电机逆变电压有效")
    private String vehtminvtrvol;

    @ApiModelProperty(value = "isg电机逆变电压有效")
    private String vehisginvtrvolv;

    @ApiModelProperty(value = "isg电机逆变电压有效")
    private String vehisginvtrvol;

    @ApiModelProperty(value = "sam电机逆变电流有效")
    private String vehsaminvtrcrntv;

    @ApiModelProperty(value = "sam电机逆变电流")
    private String vehsaminvtrcrnt;

    @ApiModelProperty(value = "sam电机状态")
    private String vehsamsta;

    @ApiModelProperty(value = "sam电机逆变温度")
    private String vehsaminvtrtem;

    @ApiModelProperty(value = "sam电机转速有效")
    private String vehsamspdv;

    @ApiModelProperty(value = "sam电机转速")
    private String vehsamspd;

    @ApiModelProperty(value = "sam电机实际转矩有效")
    private String vehsamactutoqv;

    @ApiModelProperty(value = "sam电机实际转矩")
    private String vehsamactutoq;

    @ApiModelProperty(value = "sam电机定子温度")
    private String vehsamsttrtem;

    @ApiModelProperty(value = "sam电机逆变电压有效")
    private String vehsaminvtrvolv;

    @ApiModelProperty(value = "sam电机逆变电压有效")
    private String vehsaminvtrvol;

    @ApiModelProperty(value = "sam电机故障码总数")
    private String sactotaldtc;

    @ApiModelProperty(value = "sam电机故障码列表")
    private String dtcinfomationsac;

    @ApiModelProperty(value = "bcm故障码列表")
    private String dtcinfomationbcm;

    @ApiModelProperty(value = "mc电机故障码总数")
    private String mctotaldtc;

    @ApiModelProperty(value = "mc电机故障码列表")
    private String dtcinfomationmc;

    @ApiModelProperty(value = "钥匙点火状态")
    private String vehkeyswitchstateign;

    @ApiModelProperty(value = "刹车灯开关状态")
    private String vehbrakelightswitch;

    @ApiModelProperty(value = "刹车灯开关状态有效位")
    private String vehbrakelightswitchv;

    @ApiModelProperty(value = "油门开合度故障")
    private String vehpedalposnaccelf;

    @ApiModelProperty(value = "发动机转速故障")
    private String vehenspdf;

    @ApiModelProperty(value = "车辆运行模式")
    private String vehhevsystemmode;

    private String tmstatortemp;

    private String brakelightsw;

    private String brakelightswvalid;

    private String keyswitchstateign;

    private String hevsystemmode;

    private String isgtemp;

    private String gearshiftposn;

    private String gearshiftposnvalid;

    private String tmtemp;

    private String isgtemphsc2;

    private String keyswitchstateigndmsc;

    private String gearshiftposnmsc;

    private String hevsystemmodemsc;

    private String gearshiftposnvalidmsc;

    private String tmtemphsc2;

    private String pwrmdmstrruncrka;


}
