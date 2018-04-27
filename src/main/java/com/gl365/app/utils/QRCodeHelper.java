package com.gl365.app.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * //TODO:改为前端生成二维码,后面需要删除该类和删除gradle里面的com.google.zxing:core:3.3.0引入
 * 二维码相关工具类
 * Created by caoyilong on 2017/6/5.
 */
public class QRCodeHelper {


	protected final static Logger LOG = LoggerFactory.getLogger(QRCodeHelper.class);
	private static final int QR_COLOR = 0xFF000000;   //默认是黑色
	private static final int BG_WHITE = 0xFFFFFFFF;   //背景颜色
	private static final String LOGO_PATH = "/BOOT-INF/classes/LOGO.png";


	/**
	 * 生成二维码bufferedImage图片
	 *
	 * @param content 编码内容
	 * @param width   图片宽度
	 * @param height  图片高度
	 * @return
	 */

	public static BufferedImage createQRcode(String content, int width, int height) {
		BitMatrix bm = null;
		BufferedImage image = null;
		// 用于设置QR二维码参数
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 设置QR二维码的纠错级别
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 设置编码方式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			// 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
			bm = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			int w = bm.getWidth();
			int h = bm.getHeight();
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			// 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					image.setRGB(x, y, bm.get(x, y) ? QR_COLOR : BG_WHITE);
				}
			}
		} catch (WriterException e) {
			LOG.error("生成二维码出错 > > >", e);
		}
		return image;
	}

	/**
	 * 给二维码加logo
	 *
	 * @param bufferedImage 二维码图片
	 * @return String 二维码的imageBase64字符串
	 */
	public static String addLogo(BufferedImage bufferedImage) {
		try {
			/** * 读取二维码图片，并构建绘图对象 */
			BufferedImage image = bufferedImage;
			Graphics2D g = image.createGraphics();

			/** * 读取Logo图片 */
			InputStream inputStream = QRCodeHelper.class.getResourceAsStream(LOGO_PATH);
			if (null != inputStream) {
				BufferedImage logo = ImageIO.read(inputStream);
				/** * 设置logo的大小*/
				int widthLogo = logo.getWidth(null) > image.getWidth() * 3 / 10 ? (image.getWidth() * 3 / 10) : logo.getWidth(null);
				int heightLogo = logo.getHeight(null) > image.getHeight() * 3 / 10 ? (image.getHeight() * 3 / 10) : logo.getWidth(null);

				/** * logo放在中心 */
				int x = (image.getWidth() - widthLogo) / 2;
				int y = (image.getHeight() - heightLogo) / 2;

				//开始绘制图片
				g.drawImage(logo, x, y, widthLogo, heightLogo, null);
				g.dispose();
				logo.flush();
				image.flush();
			}
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byteArrayOutputStream.flush();
			ImageIO.write(image, "png", byteArrayOutputStream);
			String imageBase64QRCode = Base64.toBase64String(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			return imageBase64QRCode;
		} catch (Exception e) {
			LOG.error("加logo出错 > > >", e);
		}
		return null;
	}

}
