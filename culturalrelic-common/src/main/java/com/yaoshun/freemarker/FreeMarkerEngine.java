package com.yaoshun.freemarker;

import com.yaoshun.constant.CommonConstants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;

/**
 * freemarker生成器
 *
 * @author King
 * @since 2019/01/16 1:46
 */
@Component
public class FreeMarkerEngine {

    private static final Logger LOG = LoggerFactory.getLogger(FreeMarkerEngine.class);

    private Configuration cfg = null;

    @PostConstruct
    public void init() {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setLocalizedLookup(false);
        cfg.setClassicCompatible(true);
    }

    public String writer(String templateName, Map root) throws IOException, TemplateException {
        LOG.debug("FreeMarkerEngine writer,templateName={}, root={}", templateName, root);
        if (StringUtils.isEmpty(templateName)) {
            LOG.error("templateName is empty");
            return null;
        }
        Template template = cfg.getTemplate(templateName);
        Writer out = new StringWriter();
        template.process(root, out);
        return out.toString();
    }

    public void writer(Map<String, Object> root, String templatePath, String outputFile) throws Exception {
        LOG.debug("FreeMarkerEngine writer, root={}, templatePath={}, outputFile={}", root, templatePath, outputFile);
        Template template = cfg.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            template.process(root, new OutputStreamWriter(fileOutputStream, CommonConstants.DEFAULT_CHARSET));
        }
    }

}
