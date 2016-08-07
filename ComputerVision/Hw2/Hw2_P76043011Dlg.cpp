
// Hw2_P76043011Dlg.cpp : ��@��
//

#include "stdafx.h"
#include "Hw2_P76043011.h"
#include "Hw2_P76043011Dlg.h"
#include "afxdialogex.h"
#include <opencv\cv.h>
#include <opencv\cxcore.h>
#include <opencv\highgui.h>
#include <opencv2\highgui\highgui.hpp>
#include <iostream>

using namespace std;
using namespace cv;

bool onBnClickedButton1 = false;
bool onBnClickedButton2 = false;
Mat result1;
Mat result2;

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// �� App About �ϥ� CAboutDlg ��ܤ��

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// ��ܤ�����
	enum { IDD = IDD_ABOUTBOX };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV �䴩

// �{���X��@
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CHw2_P76043011Dlg ��ܤ��



CHw2_P76043011Dlg::CHw2_P76043011Dlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CHw2_P76043011Dlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CHw2_P76043011Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CHw2_P76043011Dlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON1, &CHw2_P76043011Dlg::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &CHw2_P76043011Dlg::OnBnClickedButton2)
	ON_BN_CLICKED(IDC_BUTTON3, &CHw2_P76043011Dlg::OnBnClickedButton3)
	ON_BN_CLICKED(IDC_BUTTON4, &CHw2_P76043011Dlg::OnBnClickedButton4)
END_MESSAGE_MAP()


// CHw2_P76043011Dlg �T���B�z�`��

BOOL CHw2_P76043011Dlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// �N [����...] �\���[�J�t�Υ\���C

	// IDM_ABOUTBOX �����b�t�ΩR�O�d�򤧤��C
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// �]�w����ܤ�����ϥܡC�����ε{�����D�������O��ܤ���ɡA
	// �ج[�|�۰ʱq�Ʀ��@�~
	SetIcon(m_hIcon, TRUE);			// �]�w�j�ϥ�
	SetIcon(m_hIcon, FALSE);		// �]�w�p�ϥ�

	// TODO: �b���[�J�B�~����l�]�w
	AllocConsole();
	freopen ("CONOUT$", "w", stdout );

	return TRUE;  // �Ǧ^ TRUE�A���D�z�ﱱ��]�w�J�I
}

void CHw2_P76043011Dlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// �p�G�N�̤p�ƫ��s�[�J�z����ܤ���A�z�ݭn�U�C���{���X�A
// �H�Kø�s�ϥܡC���ϥΤ��/�˵��Ҧ��� MFC ���ε{���A
// �ج[�|�۰ʧ������@�~�C

void CHw2_P76043011Dlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // ø�s���˸m���e

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// �N�ϥܸm����Τ�ݯx��
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// �yø�ϥ�
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// ��ϥΪ̩즲�̤p�Ƶ����ɡA
// �t�ΩI�s�o�ӥ\����o�����ܡC
HCURSOR CHw2_P76043011Dlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}


void CHw2_P76043011Dlg::OnBnClickedButton1()
{
	// TODO: �b���[�J����i���B�z�`���{���X
	onBnClickedButton1=true;
	Mat scene1;
	Mat scene2;
	Mat left_disp_;
	//char* filename1="./scene1.bmp";
	//char* filename2="./scene2.bmp";
    //scene1=cvLoadImage(filename1);
    //scene2=cvLoadImage(filename2);
	string filename1="./scene1.bmp";
	string filename2="./scene2.bmp";
	scene1=imread(filename1, CV_LOAD_IMAGE_GRAYSCALE);
	scene2=imread(filename2, CV_LOAD_IMAGE_GRAYSCALE);
    //CvStereoBMState *first=cvCreateStereoBMState();
	//printf("hello1");
	StereoBM first;
	first.state->minDisparity=0;
	first.state->numberOfDisparities=16;
	first.state->SADWindowSize=3*3;
	first(scene1, scene2, left_disp_, CV_32F); //find the disparity for the specified rectified stereo pair
	//printf("hello2");
    normalize(left_disp_, result1, 0, 256, CV_MINMAX, CV_8U); 
	//printf("hello3");
	imshow("Window Size = 3*3 pixel", result1);
}


void CHw2_P76043011Dlg::OnBnClickedButton2()
{
	// TODO: �b���[�J����i���B�z�`���{���X
	onBnClickedButton2=true;
	Mat scene1;
	Mat scene2;
	Mat left_disp_;
	//char* filename1="./scene1.bmp";
	//char* filename2="./scene2.bmp";
    //scene1=cvLoadImage(filename1);
    //scene2=cvLoadImage(filename2);
	string filename1="./scene1.bmp";
	string filename2="./scene2.bmp";
	scene1=imread(filename1, CV_LOAD_IMAGE_GRAYSCALE);
	scene2=imread(filename2, CV_LOAD_IMAGE_GRAYSCALE);
	//printf("hello1");
    //CvStereoBMState *second=cvCreateStereoBMState();
	StereoBM second;
	second.state->minDisparity=0;
	second.state->numberOfDisparities=16;
	second.state->SADWindowSize=7*7;
	//cvFindStereoCorrespondenceBM(scene1, scene2, left_disp_, second); 
	second(scene1, scene2, left_disp_, CV_32F); //find the disparity for the specified rectified stereo pair
	//printf("hello2");
    normalize(left_disp_, result2, 0, 256, CV_MINMAX, CV_8U); 
	//printf("hello3");
	imshow("Window Size = 7*7 pixel", result2);
}


void CHw2_P76043011Dlg::OnBnClickedButton3()
{
	// TODO: �b���[�J����i���B�z�`���{���X
	if(onBnClickedButton1=false)
		CHw2_P76043011Dlg::OnBnClickedButton1();
	Mat truedisp;
	Mat dst1;
	string filename3="./truedisp.bmp";
	truedisp=imread(filename3, CV_LOAD_IMAGE_GRAYSCALE);
	int rows, cols;
	int truedispSize;
	rows=truedisp.rows;
	cols=truedisp.cols;
	truedispSize = rows*cols;
	absdiff(result1, truedisp, dst1);
	double averageerror1;
	averageerror1=(double)sum(dst1)[0]/(double)truedispSize; //computes the average error
	cout<<"Window Size =3*3 pixel, average error ="<<averageerror1<<endl;
}


void CHw2_P76043011Dlg::OnBnClickedButton4()
{
	// TODO: �b���[�J����i���B�z�`���{���X
	if(onBnClickedButton1=false)
		CHw2_P76043011Dlg::OnBnClickedButton1();
	Mat truedisp;
	Mat dst2;
	string filename3="./truedisp.bmp";
	truedisp=imread(filename3, CV_LOAD_IMAGE_GRAYSCALE);
	int rows, cols;
	int truedispSize;
	rows=truedisp.rows;
	cols=truedisp.cols;
	truedispSize=rows*cols;
	absdiff(result2, truedisp, dst2);
	double averageerror2;
	averageerror2=(double)sum(dst2)[0]/(double)truedispSize; //computes the average error
	cout<<"Window Size =3*3 pixel, average error ="<<averageerror2<<endl;
}
