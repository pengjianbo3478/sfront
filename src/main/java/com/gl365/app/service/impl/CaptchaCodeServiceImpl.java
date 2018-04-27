package com.gl365.app.service.impl;

import com.gl365.app.dto.CaptchaCodeResult;
import com.gl365.app.service.CaptchaCodeService;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.DistortionFilter;
import com.gl365.app.utils.SecureRandomUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Service("captchaCodeService")
public class CaptchaCodeServiceImpl implements InitializingBean,
		ResourceLoaderAware, CaptchaCodeService {
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private ResourceLoader resourceLoader;
	private String fontPath = "classpath:TypeWritersSubstitute-Black.ttf";
	private Font font;
	private int size = 4;
	private int fontHeight = 24;
	private List<DistortionFilter> perLetterDistortionFilters = Collections
			.emptyList();

	private int letterMSize;
	private int totalHeight;
	private int padding = 5;

	@Override
	public CaptchaCodeResult generateCaptchaCode() {
		CaptchaCodeResult result = new CaptchaCodeResult();
		StringBuilder sb = new StringBuilder(size);

		FontRenderContext frc = new FontRenderContext(new AffineTransform(),
				true, false);

		int width = letterMSize * size + padding * 2;
		BufferedImage image = new BufferedImage(width, totalHeight,
				BufferedImage.TYPE_INT_RGB);
		image.coerceData(false);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, image.getWidth(), image.getHeight());

		BufferedImage chImage = new BufferedImage(2 * letterMSize,
				2 * totalHeight, BufferedImage.TYPE_INT_ARGB);
		chImage.coerceData(false);
		Graphics2D chG2d = chImage.createGraphics();
		chG2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		chG2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// 以透明背景画出图形验证码。使用透明背景的原因是DistortionFilter的处理如果以非透明色做背景，那么例如位移的操作会导致背景色盖住验证码，得不出期待的效果）
		chG2d.setBackground(new Color(0, true));
		chG2d.setColor(Color.BLACK);
		chG2d.setFont(font);
		chG2d.setStroke(new BasicStroke(1.0f));

		int left = padding;
		char[] chs = new char[1];
		for (int i = 0; i < size; ++i) {
			char ch = generateCh();
			chs[0] = ch;
			sb.append(ch);

			chG2d.clearRect(0, 0, chImage.getWidth(), chImage.getHeight());
			GlyphVector gv = font.createGlyphVector(frc, chs);
			Shape shape = gv.getOutline();

			Rectangle2D rect = shape.getBounds2D();

			AffineTransform saveAt = chG2d.getTransform();

			chG2d.translate(
					(int) Math.round((chImage.getWidth() - rect.getWidth()) / 2),
					(int) (Math.round((chImage.getHeight() + rect.getHeight()) / 2)));
			chG2d.setColor(Color.BLACK);
			chG2d.fill(shape);
			chG2d.setColor(Color.GREEN);
			chG2d.draw(shape);

			chG2d.setTransform(saveAt);

			BufferedImage filtered = chImage;
			for (DistortionFilter filter : perLetterDistortionFilters) {
				filtered = filter.apply(filtered);
			}
			int filteredWidth = filtered.getWidth();
			int filteredHeight = filtered.getHeight();

			g2d.drawImage(filtered, left - ((filteredWidth - letterMSize) / 2),
					(totalHeight - filteredHeight) / 2, null);

			left += letterMSize;
		}

		chG2d.dispose();
		g2d.dispose();

		result.setCode(sb.toString());
		result.setImage(image);
		return result;
	}

	private char generateCh() {
		// 数字+字母区分大小写，一共62种
		int b = SecureRandomUtils.randomInt(62);
		char ch;
		if (b < 10) {
			ch = (char) ('0' + b);
		} else if (b < 26 + 10) {
			ch = (char) ('a' + (b - 10));
		} else {
			ch = (char) ('A' + (b - (26 + 10)));
		}
		return ch;
	}


	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Font rawFont = Font.createFont(Font.TRUETYPE_FONT, this.resourceLoader
				.getResource(fontPath).getInputStream());
		font = rawFont.deriveFont(((Number) fontHeight).floatValue());
		FontRenderContext frc = new FontRenderContext(new AffineTransform(),
				true, false);
		Rectangle2D rect = font.getStringBounds("M", frc);
		letterMSize = (int) Math.ceil(rect.getWidth());
		totalHeight = (int) Math.ceil(rect.getHeight());
	}

}
