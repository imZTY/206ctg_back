package io.renren;

import io.renren.datasources.DynamicDataSourceConfig;
import io.renren.modules.CTG.utils.FileUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.io.IOException;


//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@Import({DynamicDataSourceConfig.class})
public class RenrenApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		File sampleDir = new File(FileUtil.samplePath);
		File lineDir = new File(FileUtil.linePath);
		File cacheDir = new File(FileUtil.cachePath);
		try {
			if (!sampleDir.exists()){
				sampleDir.mkdirs();
			}
			FileUtil.samplePath = sampleDir.getCanonicalPath();

			if (!lineDir.exists()){
				lineDir.mkdirs();
			}
			FileUtil.linePath = lineDir.getCanonicalPath();

			if (!cacheDir.exists()){
				cacheDir.mkdirs();
			}
			FileUtil.cachePath = cacheDir.getCanonicalPath();

		} catch (IOException e) {
			e.printStackTrace();
		}
//		File rootPathDir = new File(FileUtil.rootPath);
//		try {
//			System.out.println(rootPathDir.getCanonicalPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		SpringApplication.run(RenrenApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RenrenApplication.class);
	}
}
