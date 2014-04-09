package br.fucapi.monitori.converter;

import java.lang.reflect.ParameterizedType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.fucapi.monitori.model.bean.AbstractEntityBean;

@FacesConverter(value = "converter")
public class GenericConverter<T extends AbstractEntityBean> implements
		Converter {
	@Inject
	private transient Logger logger;
	private T entity;

	private Class<T> getClassType() {
		//Class<?> superClass = getClass().getSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente,
			String stringValue) {
		if (stringValue.trim().equals("")) {
			return null;
		} else {
			try {
				Long id = Long.parseLong(stringValue);
				entity = getClassType().newInstance();
				entity.setId(id);
				logger.debug("entity.getId(): " + entity.getId());
				return entity;
			} catch (NumberFormatException e) {
				logger.debug("NumberFormatException: "+e.getMessage());
			} catch (InstantiationException e) {
				logger.debug("InstantiationException: "+e.getMessage());
			} catch (IllegalAccessException e) {
				logger.debug("IllegalAccessException: "+e.getMessage());
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object objValue) {
		if (objValue != null && !objValue.equals("")
				&& objValue instanceof AbstractEntityBean) {
			Long id = ((AbstractEntityBean) objValue).getId();
			return String.valueOf(id);
		}
		return null;
	}

}
