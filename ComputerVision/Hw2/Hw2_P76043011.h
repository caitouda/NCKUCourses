
// Hw2_P76043011.h : PROJECT_NAME ���ε{�����D�n���Y��
//

#pragma once

#ifndef __AFXWIN_H__
	#error "�� PCH �]�t���ɮ׫e���]�t 'stdafx.h'"
#endif

#include "resource.h"		// �D�n�Ÿ�


// CHw2_P76043011App:
// �аѾ\��@�����O�� Hw2_P76043011.cpp
//

class CHw2_P76043011App : public CWinApp
{
public:
	CHw2_P76043011App();

// �мg
public:
	virtual BOOL InitInstance();

// �{���X��@

	DECLARE_MESSAGE_MAP()
};

extern CHw2_P76043011App theApp;