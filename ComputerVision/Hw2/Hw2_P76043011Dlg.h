
// Hw2_P76043011Dlg.h : ���Y��
//

#pragma once


// CHw2_P76043011Dlg ��ܤ��
class CHw2_P76043011Dlg : public CDialogEx
{
// �غc
public:
	CHw2_P76043011Dlg(CWnd* pParent = NULL);	// �зǫغc�禡

// ��ܤ�����
	enum { IDD = IDD_HW2_P76043011_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV �䴩


// �{���X��@
protected:
	HICON m_hIcon;

	// ���ͪ��T�������禡
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
//	afx_msg void OnBnClickedButton2();
//	afx_msg void OnBnClickedButton1();
//	afx_msg void OnBnClickedButton3();
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
	afx_msg void OnBnClickedButton3();
	afx_msg void OnBnClickedButton4();
};
