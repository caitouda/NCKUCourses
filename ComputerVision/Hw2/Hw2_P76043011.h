
// Hw2_P76043011.h : PROJECT_NAME 應用程式的主要標頭檔
//

#pragma once

#ifndef __AFXWIN_H__
	#error "對 PCH 包含此檔案前先包含 'stdafx.h'"
#endif

#include "resource.h"		// 主要符號


// CHw2_P76043011App:
// 請參閱實作此類別的 Hw2_P76043011.cpp
//

class CHw2_P76043011App : public CWinApp
{
public:
	CHw2_P76043011App();

// 覆寫
public:
	virtual BOOL InitInstance();

// 程式碼實作

	DECLARE_MESSAGE_MAP()
};

extern CHw2_P76043011App theApp;