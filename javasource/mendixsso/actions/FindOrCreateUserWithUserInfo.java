// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package mendixsso.actions;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import mendixsso.implementation.UserManager;
import mendixsso.implementation.utils.ForeignIdentityUtils;
import mendixsso.implementation.utils.OpenIDUtils;
import mendixsso.implementation.utils.UserProfileUtils;
import mendixsso.proxies.UserProfile;
import mendixsso.proxies.constants.Constants;

public class FindOrCreateUserWithUserInfo extends CustomJavaAction<IMendixObject>
{
	private java.lang.String UserInfoJSON;

	public FindOrCreateUserWithUserInfo(IContext context, java.lang.String UserInfoJSON)
	{
		super(context);
		this.UserInfoJSON = UserInfoJSON;
	}

	@java.lang.Override
	public IMendixObject executeAction() throws Exception
	{
		// BEGIN USER CODE
        final ILogNode LOG = Core.getLogger(Constants.getLogNode());
        final IContext context = Core.createSystemContext();
        IMendixObject user = null;
        String uuid = null;
        try {
            final UserProfile userProfile = UserProfileUtils.getUserProfile(context, UserInfoJSON);

            // We assume that openId cannot be null since that would imply bigger issues higher up in the SSO stack.
            uuid = OpenIDUtils.extractUUID(userProfile.getOpenId());
            ForeignIdentityUtils.lockForeignIdentity(uuid);

            user = UserManager.findOrCreateUser(userProfile).getMendixObject();
        } catch (Throwable e) {
            LOG.error("Something went wrong while provisioning the user with the provided user info", e);
        } finally {
            if (uuid != null) {
                ForeignIdentityUtils.unlockForeignIdentity(uuid);
            }
        }
        return user;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "FindOrCreateUserWithUserInfo";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}