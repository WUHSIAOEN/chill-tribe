package web.googlelogin.service;

import core.vo.GoogleData;
import web.member.vo.Member;

public interface GoogleService {

	Member googlelogin(GoogleData gd);
}
