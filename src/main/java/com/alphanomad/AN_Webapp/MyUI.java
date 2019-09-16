package com.alphanomad.AN_Webapp;

import javax.servlet.annotation.WebServlet;

import com.alphanomad.AN_Webapp.DBHelper;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI
{

	Navigator navigator;
	private UserInfo user_info;
	protected static final String TUTORMAINVIEW = "tutormain";
	protected static final String LECTMAINVIEW = "lectmain";
	protected static final String ADMINMAINVIEW = "adminmain";
	protected static final String MAINVIEW = "main";
	protected static final String PROFILEVIEW = "profile";
	protected static final String LOGINVIEW = "login";
	protected static final String REGVIEW = "register";
	protected static final String HISTORYVIEW = "history";
	protected static final String CLAIMFORM = "claimform";
	protected static final String CONFIRMCLAIMFORM = "confirm";
	public boolean logged_in = false;

	@Override
	public void init(VaadinRequest request)
	{
		addStyleName("image-backgound");
		getPage().setTitle("Alpha Nomad");

		// Create a navigator to control the views
		navigator = new Navigator(this, this);

		// Create and register the views
		navigator.addView(TUTORMAINVIEW, new TutorMainView());
		navigator.addView(LECTMAINVIEW, new LectMainView());
		navigator.addView(ADMINMAINVIEW, new AdminMainView());
		navigator.addView(PROFILEVIEW, new ProfileView(this));
		navigator.addView(LOGINVIEW, new LoginView(this));
		navigator.addView(REGVIEW, new Register());
		navigator.addView(HISTORYVIEW, new ClaimHistory());
		navigator.addView(PROFILEVIEW, new ProfileView(this));
		navigator.addView(LOGINVIEW, new LoginView(this));
		navigator.addView(REGVIEW, new Register());
		navigator.addView(HISTORYVIEW, new ClaimHistory());
		navigator.addView(CLAIMFORM, new ClaimForm());
		navigator.addView(CONFIRMCLAIMFORM, new ConfirmClaimForm(null, null, null, null, null, null, null, null));

		Responsive.makeResponsive(this);
		navigator.navigateTo(LOGINVIEW);
		System.out.println("regstration complete");

		Responsive.makeResponsive(this);
		if (!logged_in)
		{
			navigator.navigateTo(LOGINVIEW);
		} else
		{
			navigator.navigateTo(MAINVIEW);
		}

	}

	public void set_user_info(UserInfo info)
	{
		this.user_info = info;
	}

	public UserInfo get_user_info()
	{
		System.out.println("setting user info");
		System.out.println(this.user_info.hashCode());
		return this.user_info;
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
	public static class MyUIServlet extends VaadinServlet
	{
	}
}