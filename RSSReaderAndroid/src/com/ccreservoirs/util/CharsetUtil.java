package com.ccreservoirs.util;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.net.URL;
import java.nio.charset.Charset;

public class CharsetUtil {

	/**
	 * 利用第三方开源包cpdetector获取文件编码格式.
	 *
	 * @param filePath
	 * @return
	 */
	public static Charset getFileEncode(String filePath) {
		/**
		 * <pre>
		 * 1、cpDetector内置了一些常用的探测实现类,这些探测实现类的实例可以通过add方法加进来,如:ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector.
		 * 2、detector按照“谁最先返回非空的探测结果,就以该结果为准”的原则.
		 * 3、cpDetector是基于统计学原理的,不保证完全正确.
		 * </pre>
		 */
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());// 需要第三方JAR包:antlr.jar、chardet.jar.
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		try {
			charset = detector.detectCodepage(new URL(filePath));
		} catch (Exception e) {
			// log.error("", e);
		}
		return charset;
	}
}
