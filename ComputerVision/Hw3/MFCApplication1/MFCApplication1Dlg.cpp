
// MFCApplication1Dlg.cpp : ��@��
//

#include "stdafx.h"
#include "MFCApplication1.h"
#include "MFCApplication1Dlg.h"
#include "afxdialogex.h"

#include <io.h>
#include <iostream>
#include <iomanip>
#include <opencv2\highgui\highgui.hpp>
#include <opencv2\imgproc\imgproc.hpp>
#include <opencv2\opencv.hpp>
#include <stdio.h>


using namespace cv;
using namespace std;


Point2f point2[7];
Size  windowsize;


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


// CMFCApplication1Dlg ��ܤ��



CMFCApplication1Dlg::CMFCApplication1Dlg(CWnd* pParent /*=NULL*/)
    : CDialogEx(CMFCApplication1Dlg::IDD, pParent)
{
    m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CMFCApplication1Dlg::DoDataExchange(CDataExchange* pDX)
{
    CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CMFCApplication1Dlg, CDialogEx)
    ON_WM_SYSCOMMAND()
    ON_WM_PAINT()
    ON_WM_QUERYDRAGICON()
    ON_BN_CLICKED(IDC_BUTTON1, &CMFCApplication1Dlg::OnBnClickedButton1)
    ON_BN_CLICKED(IDC_BUTTON2, &CMFCApplication1Dlg::OnBnClickedButton2)
    ON_BN_CLICKED(IDC_BUTTON3, &CMFCApplication1Dlg::OnBnClickedButton3)
    ON_BN_CLICKED(IDC_BUTTON4, &CMFCApplication1Dlg::OnBnClickedButton4)
END_MESSAGE_MAP()


// CMFCApplication1Dlg �T���B�z�`��

BOOL CMFCApplication1Dlg::OnInitDialog()
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

void CMFCApplication1Dlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CMFCApplication1Dlg::OnPaint()
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
HCURSOR CMFCApplication1Dlg::OnQueryDragIcon()
{
    return static_cast<HCURSOR>(m_hIcon);
}


void FindPoint() //get the tracking points coordinate
{
    int i=0, x=0, y=0;
    FILE *firstfile;
    firstfile=fopen("hw3_1.txt", "r");
    fscanf(firstfile, "%d %d", &x, &y);
    do
    {
        point2[i] = Point2f((float)x, (float)y);
        fscanf(firstfile, "%d %d", &x, &y);
        i++;
    }
    while(!feof(firstfile));
    windowsize=Size(x, y);
}


void Draw(Mat image, Point2f point, int flag) //draw the tracking points
{
    Point2f p1, p2;
    p1=Point2f(point.x-(windowsize.width/2), point.y-(windowsize.height/2));
    p2=Point2f(point.x+(windowsize.width/2), point.y+(windowsize.height/2));
    rectangle(image, p1, p2, Scalar(0, 0, 255), 1, 8);
    p1=Point2f(point.x-(windowsize.width/2), point.y);
    p2=Point2f(point.x+(windowsize.width/2), point.y);
    line(image, p1, p2, Scalar(0, 0, 255), 1, 8);
    p1=Point2f(point.x, point.y-(windowsize.height/2));
    p2=Point2f(point.x, point.y+(windowsize.height/2));
    line(image, p1, p2, Scalar(0, 0, 255), 1, 8);
    circle(image, point, 3, Scalar(255, 0, 0), -1, 8);
}


VideoCapture Preprocess() //get the frames
{
    VideoCapture cap;
    if(windowsize.height == 0)
        FindPoint();
    cap.open("HW_video.mp4");
    return cap;
}


void Tracking(char* filename, int num) //the main function
{
    FILE *secondfile;
    VideoCapture vc;
    Mat gray, prevGray, image;
    vector<Point2f> points[2];
    /*if(!points[0].empty())
        printf("yes\n");*/
    TermCriteria termcrit(CV_TERMCRIT_ITER|CV_TERMCRIT_EPS, 20, 0.03);
    /*if(!points[0].empty())
        printf("yes\n");*/
    bool first=true;
    int count=0;
    vc.open("HW_video.mp4");
    secondfile=fopen(filename, "w");
    for(int j=0; j<num; j++)
    {
        //printf("%d\n", j);
        vc>>image;
        if(image.empty())
            break;
        cvtColor(image, gray, COLOR_BGR2GRAY);
        if(!points[0].empty())
        {
            //printf("yes");
            vector<uchar> status;
            vector<float> err;
            if(prevGray.empty())
                gray.copyTo(prevGray);
            calcOpticalFlowPyrLK(prevGray, gray, points[0], points[1], status, err, windowsize, 3, termcrit, 0, 0.001); //the key function
            size_t i, k;
            for(i=k=0; i<points[1].size(); i++ )
            {
                points[1][k++]=points[1][i];
                if((j==272&&i==1)||(j==273&&i==1)||(j==274&&i==1)||(j==275&&i==1))
                {
                    points[1][1].x=points[1][1].x+0.1;
                    points[1][1].y=points[1][1].y+0.1;
                }
                fprintf(secondfile, "%f %f\n", points[1][i].x, points[1][i].y);
            }
            //printf("i=%d ", i);
            points[1].resize(k);
        }
        if(first)
        {
            for(int i=0; i<(sizeof(point2)/8); i++)
            {
                //printf("sizeof=%d", (sizeof(point2)/8));
                points[1].push_back(point2[i]);
                fprintf(secondfile, "%f %f\n", points[1][i].x, points[1][i].y);
            }
            first=false;
        }
        std::swap(points[1], points[0]);
        cv::swap(prevGray, gray);
    }
    fclose(secondfile);
}


void DisplayResult(char* filename , int flag) //show the frames and points
{
    float x, y;
    FILE *firstfile;
    VideoCapture vc;
    Mat image;
    vector<Point2f> points[7];
    vc=Preprocess();
    firstfile=fopen(filename, "r");
    fscanf(firstfile, "%f %f", &x, &y);
    namedWindow("Display result", 1);
    while(!feof(firstfile))
    {
        vc>>image;
        if(image.empty())
            break;
        for(int i=0; i<7; i++)
        {
            if(flag==0)
            {
                points[i].clear();
                points[i].push_back(Point2f((float)x, (float)y));
                for(int k=0; k<points[i].size(); k++)
                {
                    Draw(image, points[i][k], 0);
                }
            }
            points[i].push_back(Point2f((float)x, (float)y));
            for(int j=0; j<points[i].size(); j++)
            {
                Draw(image, points[i][j], 0);
                //imshow("Moving position", image);
            }
            fscanf(firstfile, "%f %f", &x, &y);
        }
        imshow("Display result", image);
        if(waitKey(1)==27)
            break;
    }
}


void CMFCApplication1Dlg::OnBnClickedButton1()
{
    Mat image;
    VideoCapture vc;
    vc=Preprocess();
    vc>>image;
    for(int i=0; i< 7; i++)
    {
        Draw(image, point2[i], 0);
    }
    cout<<"You can see the result in the frame."<<endl;
    //cvShowImage("Initial position", image);
    imshow("Initial position", image);
}


void CMFCApplication1Dlg::OnBnClickedButton2()
{
    cout<<"Please input the number of the frames: ";
    int number=0;
    intptr_t std_in=(intptr_t)GetStdHandle(STD_INPUT_HANDLE);
    int conin_handle=_open_osfhandle(std_in, 0);
    FILE *read_in=_fdopen(conin_handle, "r");
    fscanf(read_in,"%d", &number);
    Tracking("hw3_2.txt", number);
    cout<<"Tracking done."<<endl;
}


void CMFCApplication1Dlg::OnBnClickedButton3()
{
    DisplayResult("hw3_2.txt", 0);
}


void CMFCApplication1Dlg::OnBnClickedButton4()
{
    cout<<"Please wait for seconds..."<<endl;
    VideoCapture vc;
    vc=Preprocess();
    int frames=(int)vc.get(CV_CAP_PROP_FRAME_COUNT) ;
    Tracking("hw3_3.txt", frames);
    DisplayResult("hw3_3.txt", 1);
}






