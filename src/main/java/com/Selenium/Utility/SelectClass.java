package com.Selenium.Utility;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectClass extends GlobalDriverCap {

	public static SelectClass selectClass;
	public Select select;

	public static SelectClass getSelectClassInstance(WebElement element) {

		if (selectClass == null) {

			return selectClass = new SelectClass(element);

		} else {

			return selectClass;

		}

	}

	public SelectClass(WebElement ele) {

		select = new Select(ele);

	}

	public void selectAvalueInDropdown(String Value, String VisibileText, Integer index) {

		if (Value != null) {

			select.selectByValue(Value);

		} else if (VisibileText != null) {

			select.selectByVisibleText(VisibileText);

		} else if (index != null) {

			select.selectByIndex(index);

		}

	}

	public void selectAllValuesInDropdown() {

		List<WebElement> AllValuesInDropdown = select.getOptions();

		AllValuesInDropdown.forEach(K -> select.selectByValue(K.getAttribute("value")));

	}

	public void deselectAValueInDropdown(String Value, String VisibileText, Integer index) {

		if (Value != null) {

			select.deselectByValue(Value);

		} else if (VisibileText != null) {

			select.deselectByVisibleText(VisibileText);

		} else if (index != null) {

			select.deselectByIndex(index);

		}

	}

	public void deselectAllValuesInDropdown() {

		if (select.isMultiple())

			select.deselectAll();

	}

	public void getAllselectedValuesInDropdown() {

		List<WebElement> selectedValues = select.getAllSelectedOptions();
		selectedValues.forEach(K -> System.out.println(K.getText()));

	}

	public void getselectedValueInDropdown() {

		WebElement ele=select.getFirstSelectedOption();
		System.out.println(ele.getText());
	}

}
