package com.spay.web.config;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.spay.core.constants.JsonConstant;
import com.spay.web.common.helper.PagerInfo;


/**
 * json 요청에 대하여 공통 result 구조로 리턴한다.
 *
 * <pre>
 * - json 결과 양식
 * 
 *   {
 *       result : true | false // 결과
 *       data : {} // success/fail callback 함수 parameter
 *       invalid : [] // invalid 처리에 사용될 parameter
 *       message : "" // 출력메세지. 필요시에만 사용.
 *   }
 * </pre>
 *
 * @author Lee Kyung Joo
 */

public class CommonMappingJackson2JsonView extends MappingJackson2JsonView {

    private static Logger logger = LoggerFactory.getLogger(CommonMappingJackson2JsonView.class);

    private boolean updateContentLength;
    @SuppressWarnings("unused")
	private String jsonPrefix;

    @Override
    public void setUpdateContentLength(boolean updateContentLength) {
        super.setUpdateContentLength(updateContentLength);
        this.updateContentLength = updateContentLength;
    }

    @Override
    public void setJsonPrefix(String jsonPrefix) {
        super.setJsonPrefix(jsonPrefix);
        this.jsonPrefix = jsonPrefix;
    }

    @Override
    public void setPrefixJson(boolean prefixJson) {
        super.setPrefixJson(prefixJson);
        this.jsonPrefix = "{} && ";
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        response.setStatus(HttpStatus.OK.value());
        // output stream
        OutputStream stream = (this.updateContentLength ? createTemporaryOutputStream() : response.getOutputStream());

        // model
        Map<String, Object> value = null;
        PagerInfo pagerInfo = (PagerInfo)request.getAttribute(PagerInfo.DEFAULT_PAGER_INFO_ATTRIBUTE_KEY);

        Throwable exception = (Throwable)model.get(JsonConstant.EXCEPTION);
        if (exception != null) {
            logger.debug(">>>>>>>>>>>>>>> exception:{}", exception);
            value = getModelForFail(exception, model);
        } else {
            value = getModelForSuccess(model, pagerInfo);
        }

        // write
        writeContent(stream, value);
        if (this.updateContentLength) {
            writeToResponse(response, (ByteArrayOutputStream)stream);
        }
    }

    // fail model
    private Map<String, Object> getModelForFail(Throwable exception, Map<String, Object> model) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(JsonConstant.RESULT, false);

        Exception modelException = (Exception)exception;

        if (model.get(JsonConstant.EXCEPTION_MESSAGE) != null) {
            result.put(JsonConstant.EXCEPTION_MESSAGE, modelException.getMessage());
        }

        if (model.get(JsonConstant.INVALID) != null) {
            result.put(JsonConstant.INVALID, model.get(JsonConstant.INVALID));
        }

        return result;
    }

    // success model
    private Map<String, Object> getModelForSuccess(Map<String, Object> model, PagerInfo pagerInfo) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (model.get(JsonConstant.INVALID) != null) {
            result.put(JsonConstant.RESULT, false);
            result.put(JsonConstant.INVALID, model.get(JsonConstant.INVALID));

        } else {
            result.put(JsonConstant.RESULT, true);
        }

        if (model.get(JsonConstant.MESSAGE) != null) {
            result.put(JsonConstant.MESSAGE, model.get(JsonConstant.MESSAGE));
        }

        // MenuInterceptor 에서 추가함..
        model.remove("topMenus");
        model.remove("subMenus");

        @SuppressWarnings("unchecked") Map<String, Object> modelMap = (Map<String, Object>)super.filterModel(model);

        if (pagerInfo != null) {
            Map<String, Object> pageInfo = new HashMap<String, Object>();
            pageInfo.put(JsonConstant.TOTAL_COUNT, pagerInfo.getTotalRows());
            pageInfo.put(JsonConstant.PAGE_COUNT, pagerInfo.getPage());
            pageInfo.put(JsonConstant.DISPLAY_COUNT, pagerInfo.getPageSize());

            modelMap.put(JsonConstant.PAGE_INFO, pageInfo);
        }

        result.put(JsonConstant.DATA, modelMap);

        return result;
    }

}
