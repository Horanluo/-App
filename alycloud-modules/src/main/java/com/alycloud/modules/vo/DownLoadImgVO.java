package com.alycloud.modules.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下载身份图片-实体类
 * @author Horanluo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownLoadImgVO {

	private String frontUrl;
	private String backUrl;
	private String livingPhotoUrl;
	private String videoUrl;
}
