
// HW1Dlg.cpp : 實作檔
//

#include <iostream>
#include "stdafx.h"
#include "HW1.h"
#include "HW1Dlg.h"
#include "afxdialogex.h"
#include <stdlib.h>
#include "opencv\cv.h"
#include "opencv\cxcore.h"
#include "opencv\highgui.h"
#include "opencv2\highgui\highgui.hpp"
#include <math.h>

using namespace std;
using namespace cv;


#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// 對 App About 使用 CAboutDlg 對話方塊

class CAboutDlg : public CDialogEx
{
public:
    CAboutDlg();

// 對話方塊資料
    enum { IDD = IDD_ABOUTBOX };

protected:
    virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支援

// 程式碼實作
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


// CHW1Dlg 對話方塊



CHW1Dlg::CHW1Dlg(CWnd* pParent /*=NULL*/)
    : CDialogEx(CHW1Dlg::IDD, pParent)
{
    m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CHW1Dlg::DoDataExchange(CDataExchange* pDX)
{
    CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CHW1Dlg, CDialogEx)
    ON_WM_SYSCOMMAND()
    ON_WM_PAINT()
    ON_WM_QUERYDRAGICON()
    ON_BN_CLICKED(IDC_BUTTON1, &CHW1Dlg::OnBnClickedButton1)
    ON_BN_CLICKED(IDC_BUTTON2, &CHW1Dlg::OnBnClickedButton2)
    ON_BN_CLICKED(IDC_BUTTON3, &CHW1Dlg::OnBnClickedButton3)
    ON_BN_CLICKED(IDC_BUTTON4, &CHW1Dlg::OnBnClickedButton4)
    ON_BN_CLICKED(IDC_BUTTON5, &CHW1Dlg::OnBnClickedButton5)
    ON_BN_CLICKED(IDC_BUTTON6, &CHW1Dlg::OnBnClickedButton6)
END_MESSAGE_MAP()


// CHW1Dlg 訊息處理常式

BOOL CHW1Dlg::OnInitDialog()
{
    CDialogEx::OnInitDialog();

    // 將 [關於...] 功能表加入系統功能表。

    // IDM_ABOUTBOX 必須在系統命令範圍之中。
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

    // 設定此對話方塊的圖示。當應用程式的主視窗不是對話方塊時，
    // 框架會自動從事此作業
    SetIcon(m_hIcon, TRUE);			// 設定大圖示
    SetIcon(m_hIcon, FALSE);		// 設定小圖示

    // TODO: 在此加入額外的初始設定
    AllocConsole();
    freopen ("CONOUT$", "w", stdout );

    return TRUE;  // 傳回 TRUE，除非您對控制項設定焦點
}

void CHW1Dlg::OnSysCommand(UINT nID, LPARAM lParam)
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

// 如果將最小化按鈕加入您的對話方塊，您需要下列的程式碼，
// 以便繪製圖示。對於使用文件/檢視模式的 MFC 應用程式，
// 框架會自動完成此作業。

void CHW1Dlg::OnPaint()
{
    if (IsIconic())
    {
        CPaintDC dc(this); // 繪製的裝置內容

        SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

        // 將圖示置中於用戶端矩形
        int cxIcon = GetSystemMetrics(SM_CXICON);
        int cyIcon = GetSystemMetrics(SM_CYICON);
        CRect rect;
        GetClientRect(&rect);
        int x = (rect.Width() - cxIcon + 1) / 2;
        int y = (rect.Height() - cyIcon + 1) / 2;

        // 描繪圖示
        dc.DrawIcon(x, y, m_hIcon);
    }
    else
    {
        CDialogEx::OnPaint();
    }
}

// 當使用者拖曳最小化視窗時，
// 系統呼叫這個功能取得游標顯示。
HCURSOR CHW1Dlg::OnQueryDragIcon()
{
    return static_cast<HCURSOR>(m_hIcon);
}



char* filename="./calibration/c15.bmp";
IplImage* imgRGB = cvLoadImage(filename);
IplImage* imgRGB2 = cvLoadImage(filename);
IplImage* imgGrey = cvLoadImage(filename, CV_LOAD_IMAGE_GRAYSCALE);
int corner_row=8;//interior number of row corners
int corner_col=6;//interior number of column corners.
int corner_n=corner_row*corner_col;
CvSize pattern_size=cvSize(corner_row,corner_col);
CvPoint2D32f* corners=new CvPoint2D32f[corner_n];
int corner_count;
float left_top_x;
float left_top_y;

int n_patterns = 0;//Will be set by input list
float k1, k2, p1, p2, k3;

CvScalar Red=CV_RGB(255,0,0);
CvScalar Green=CV_RGB(0,255,0);
CvScalar Blue=CV_RGB(0,0,255);

void CHW1Dlg::OnBnClickedButton1()
{
    // TODO: 在此添加控件通知处理程序代码
    cout<<"Find the corners on the chessboard-c15 and draw them."<<endl;

    /*if (imgGrey==NULL){//image validation
       cout << "No valid image input."<<endl;
       char c=getchar();
       return 1;
    }*/

    //-------Find chessboard corners--------------

    int found=cvFindChessboardCorners(//returning non-zero means sucess.
                  imgGrey,//8-bit single channel greyscale image.
                  pattern_size,//how many INTERIOR corners in each row and column of the chessboard.
                  corners,//a pointer to an array where the corner locations can be recorded.
                  &corner_count,// optional, if non-NULL, its a point to an integer where the nuber of corners found can be recorded.
                  CV_CALIB_CB_ADAPTIVE_THRESH|CV_CALIB_CB_FILTER_QUADS//check page 382-383.
              );
    left_top_x=corners[0].x;
    left_top_y=corners[0].y;

    //-------------Obtain subpixel accuracy on those corners-----------------------
    int half_win_size=3;//the window size will be 3+1+3=7
    int iteration=20;
    double epislon=0.1;
    cvFindCornerSubPix(
        imgGrey,
        corners,
        corner_count,
        cvSize(half_win_size, half_win_size),
        cvSize(-1, -1),//no ignoring the neighbours of the center corner
        cvTermCriteria(CV_TERMCRIT_ITER|CV_TERMCRIT_EPS, iteration, epislon)
    );

    //-------Draw the corner pattern-------
    cvDrawChessboardCorners(
        imgRGB,
        pattern_size,
        corners,
        corner_count,
        found
    );
    //to summary a bit of findings.
    cvNamedWindow("Corner Detection", CV_WINDOW_AUTOSIZE );
    cvShowImage( "Corner Detection", imgRGB );
    cvWaitKey(0);
    cvReleaseImage(&imgGrey);
    cvReleaseImage(&imgRGB);
    cvDestroyWindow("Corner Detection");
}


void CHW1Dlg::OnBnClickedButton2()
{
    // TODO: 在此添加控件通知处理程序代码
    cout<<"Use the cvFindCornerSubPix to improve the accuracy and show the left top point coordinate of the chessboard-c15"<<endl;
    //to display a coordinate of the left top corner
    cout<<"left top corner before subpixel : ("<<left_top_x<<","<<left_top_y<<")"<<endl;
    cout<<"left top corner after subpixel : ("<<corners[0].x<<","<<corners[0].y<<")"<<endl;
    //cvNamedWindow("Subpixel", CV_WINDOW_AUTOSIZE);
    //cvShowImage("Subpixel", imgRGB);

    //cvWaitKey(0);
    //cvReleaseImage(&imgGrey);
    //cvReleaseImage(&imgRGB);
    //cvDestroyWindow("Subpixel");
}


/*void PrintMat(CvMat *matrix, BOOL save_or_show, FILE *fp)
{
    int i=0;
    int j=0;
    for(i=0; i < matrix->rows; i++)//行
    {
        if (save_or_show)
        {
            fprintf(fp, "\n");
        }
        else
        {
            printf("\n");
        }
        switch(matrix->type&0X07)
        {
        case CV_32F:
        case CV_64F:
        {
            for(j=0; j<matrix->cols; j++)//列
            {
                if (save_or_show)
                {
                    fprintf(fp, "%9.3f ", (float)cvGetReal2D(matrix, i, j));
                }
                else
                {
                    printf("%9.3f ", (float)cvGetReal2D(matrix, i, j));
                }

            }
            printf("\n");

            break;
        }
        case CV_8U:
        case CV_16U:
        {
            for(j=0; j<matrix->cols; j++)
            {
                printf("%6d  ",(int)cvGetReal2D(matrix, i, j));
                if (save_or_show)
                {
                    fprintf(fp, "%6d  ", (int)cvGetReal2D(matrix, i, j));
                }
                else
                {
                    printf("%6d  ", (int)cvGetReal2D(matrix, i, j));
                }
            }
            printf("\n");

            break;
        }

        default:
            break;

        }
    }

}*/


void CHW1Dlg::OnBnClickedButton3()
{
    // TODO: 在此添加控件通知处理程序代码
    cout<<"Find the intrinsic matrix:"<<endl;
    cout<<"Due to the complexity of the process, please wait for seconds:"<<endl;
    cout<<"......"<<endl;
    /*CvCapture* capture;// = cvCreateCameraCapture( 0 );
    // assert( capture );

    if(argc != 4){
    	help();
    	return -1;
    }
    help();
    corner_row = atoi(argv[1]);
    corner_col = atoi(argv[2]);
    help();*/
    FILE *fptr = fopen("HW1_chessboards.txt", "r");
    char names[2048];
    //COUNT THE NUMBER OF IMAGES:
    while(fscanf(fptr, "%s ", names)==1)
    {
        n_patterns++;
    }
    rewind(fptr);

    //cvNamedWindow("Calibration");
//ALLOCATE STORAGE
    CvMat* image_points  = cvCreateMat(n_patterns*corner_n, 2, CV_32FC1);
    CvMat* object_points = cvCreateMat(n_patterns*corner_n, 3, CV_32FC1);
    CvMat* point_counts  = cvCreateMat(n_patterns, 1, CV_32SC1);

///  CvMat * image_points  = cvCreateMat(1, n_patterns*corner_n, CV_32FC2);
///  CvMat * object_points = cvCreateMat(1, n_patterns*corner_n, CV_32FC3);
///  CvMat * point_counts  = cvCreateMat(1, n_patterns, CV_32SC1);

    CvMat* intrinsic_matrix  = cvCreateMat(3, 3, CV_32FC1);
    CvMat* distortion_coeffs = cvCreateMat(5, 1, CV_32FC1);
    CvMat* rotation_vectors  = cvCreateMat(n_patterns, 3, CV_32FC1);//是三個旋轉角度P444
    CvMat* translation_vectors = cvCreateMat(n_patterns, 3, CV_32FC1);
    CvMat* rotation_vectors15  = cvCreateMat(3, 1, CV_32FC1);
    CvMat* rotation_matrix15  = cvCreateMat(3, 3, CV_32FC1);
    CvMat* translation_vectors15 = cvCreateMat(3, 1, CV_32FC1);


    IplImage* image = 0;// = cvQueryFrame( capture );
    IplImage* gray_image = 0;//for subpixel
    CvPoint2D32f* corners = new CvPoint2D32f[corner_n];
    int successes = 0;
    int step;

    for(int frame=0; frame<n_patterns; frame++ )
    {
        fscanf(fptr, "%s ", names);

        if(image)
        {
            cvReleaseImage(&image);
            image = 0;
        }
        image = cvLoadImage(names);
        if(gray_image == 0 && image)
            gray_image = cvCreateImage(cvGetSize(image), 8, 1);

        if(!image)
            printf("null image\n");

        int found = cvFindChessboardCorners(
                        image,
                        pattern_size,
                        corners,
                        &corner_count,
                        CV_CALIB_CB_ADAPTIVE_THRESH | CV_CALIB_CB_FILTER_QUADS
                    );

        //Get Subpixel accuracy on those corners
        cvCvtColor(image, gray_image, CV_BGR2GRAY);
        cvFindCornerSubPix(gray_image, corners, corner_count,
                           cvSize(11, 11), cvSize(-1, -1), cvTermCriteria(CV_TERMCRIT_EPS+CV_TERMCRIT_ITER, 30, 0.1));
        //Draw it

        //cvDrawChessboardCorners(image, pattern_size, corners, corner_count, found);
        //cvShowImage( "Calibration", image );

        // If it is a good board, add it to our data
        //
        if( corner_count == corner_n )
        {
            step = successes*corner_n;
//	printf("Found = %d for %s\n",found,names);
            for(int i=step, j=0; j<corner_n; ++i, ++j)
            {
///         CV_MAT_ELEM(*image_points, CvPoint2D32f,0,i) = cvPoint2D32f(corners[j].x, corners[j].y);
///         CV_MAT_ELEM(*object_points,CvPoint3D32f,0,i) = cvPoint3D32f(j/corner_row, j%corner_row, 0.0f);
                CV_MAT_ELEM(*image_points, float, i, 0) = corners[j].x;
                CV_MAT_ELEM(*image_points, float, i, 1) = corners[j].y;
                CV_MAT_ELEM(*object_points,float, i, 0) = j/corner_row;
                CV_MAT_ELEM(*object_points,float, i, 1) = j%corner_row;
                CV_MAT_ELEM(*object_points,float, i, 2) = 0.0f;

            }
//        CV_MAT_ELEM(*point_counts, int,0,successes) = corner_n;
            CV_MAT_ELEM(*point_counts, int, successes, 0) = corner_n;
            successes++;
        }

//    if( successes == n_patterns ) break;

        int c = cvWaitKey(15);
        if(c == 'p')
        {
            c = 0;
            while(c != 'p' && c != 27)
            {
                c = cvWaitKey(250);
            }
        }
        /*if(c == 27)
        	return 0;*/
    }
    printf("successes = %d, n_patterns=%d\n",successes,n_patterns);
    //ALLOCATE MATRICES ACCORDING TO HOW MANY IMAGES WE FOUND CHESSBOARDS ON
///  CvMat* image_points2  = cvCreateMat(1,successes*corner_n, CV_32FC2);
///  CvMat* object_points2 = cvCreateMat(1,successes*corner_n, CV_32FC3);
///  CvMat* point_counts2  = cvCreateMat(1,successes, CV_32SC1);
    CvMat* object_points2 = cvCreateMat(successes*corner_n, 3, CV_32FC1);
    CvMat* image_points2  = cvCreateMat(successes*corner_n, 2, CV_32FC1);
    CvMat* point_counts2  = cvCreateMat(successes, 1, CV_32SC1);
    //TRANSFER THE POINTS INTO THE CORRECT SIZE MATRICES
    for(int i = 0; i<successes*corner_n; ++i)
    {
///      CV_MAT_ELEM(*image_points2, CvPoint2D32f, 0, i) = CV_MAT_ELEM(*image_points, CvPoint2D32f, 0, i);
///      CV_MAT_ELEM(*object_points2,CvPoint3D32f, 0, i) = CV_MAT_ELEM(*object_points, CvPoint3D32f, 0, i);
        CV_MAT_ELEM(*image_points2, float, i, 0) = CV_MAT_ELEM(*image_points, float, i, 0);
        CV_MAT_ELEM(*image_points2, float, i, 1) = CV_MAT_ELEM(*image_points, float, i, 1);
        CV_MAT_ELEM(*object_points2, float, i, 0) = CV_MAT_ELEM(*object_points, float, i, 0);
        CV_MAT_ELEM(*object_points2, float, i, 1) = CV_MAT_ELEM(*object_points, float, i, 1);
        CV_MAT_ELEM(*object_points2, float, i, 2) = CV_MAT_ELEM(*object_points, float, i, 2);

    }
    for(int i=0; i<successes; ++i)
    {
///		CV_MAT_ELEM(*point_counts2, int, 0, i) = CV_MAT_ELEM(*point_counts, int, 0, i);
        CV_MAT_ELEM(*point_counts2, int, i, 0) = CV_MAT_ELEM(*point_counts, int, i, 0);
    }
    cvReleaseMat(&object_points);
    cvReleaseMat(&image_points);
    cvReleaseMat(&point_counts);

    // Initialize the intrinsic matrix such that the two focal
    // lengths have a ratio of 1.0
    //
    CV_MAT_ELEM(*intrinsic_matrix, float, 0, 0) = 1.0f;
    CV_MAT_ELEM(*intrinsic_matrix, float, 1, 1) = 1.0f;
    printf("cvCalibrateCamera2\n");
    cvCalibrateCamera2(
        object_points2,
        image_points2,
        point_counts2,
        cvGetSize( image ),
        intrinsic_matrix,
        distortion_coeffs,
        rotation_vectors,
        translation_vectors,
        0//CV_CALIB_FIX_ASPECT_RATIO
    );
    int i=0;
    int j;
    printf("[");
    for(j=0; j<2; j++)
    {
        printf("%9.3f ", (float)cvGetReal2D(intrinsic_matrix, i, j));
    }
    printf("%9.3f;\n", (float)cvGetReal2D(intrinsic_matrix, i, j));
    i++;
    printf(" ");
    for(j=0; j<2; j++)
    {
        printf("%9.3f ", (float)cvGetReal2D(intrinsic_matrix, i, j));
    }
    printf("%9.3f;\n", (float)cvGetReal2D(intrinsic_matrix, i, j));
    i++;
    printf(" ");
    for(j=0; j<2; j++)
    {
        printf("%9.3f ", (float)cvGetReal2D(intrinsic_matrix, i, j));
    }
    printf("%9.3f]\n", (float)cvGetReal2D(intrinsic_matrix, i, j));
    k1 = (float)cvGetReal2D(distortion_coeffs, 0, 0);
    k2 = (float)cvGetReal2D(distortion_coeffs, 1, 0);
    p1 = (float)cvGetReal2D(distortion_coeffs, 2, 0);
    p2 = (float)cvGetReal2D(distortion_coeffs, 3, 0);
    k3 = (float)cvGetReal2D(distortion_coeffs, 4, 0);
    rotation_vectors15->data.fl[0] = (float)cvGetReal2D(rotation_vectors, 14, 0);
    rotation_vectors15->data.fl[1] = (float)cvGetReal2D(rotation_vectors, 14, 1);
    rotation_vectors15->data.fl[2] = (float)cvGetReal2D(rotation_vectors, 14, 2);
    //printf("%9.3f\n", rotation_vector15->data.fl[0]);
    //printf("%9.3f\n", rotation_vector15->data.fl[1]);
    //printf("%9.3f\n", rotation_vector15->data.fl[2]);
    translation_vectors15->data.fl[0] = (float)cvGetReal2D(translation_vectors, 14, 0);
    translation_vectors15->data.fl[1] = (float)cvGetReal2D(translation_vectors, 14, 1);
    translation_vectors15->data.fl[2] = (float)cvGetReal2D(translation_vectors, 14, 2);
    //printf("%9.3f\n", translation_vectors15->data.fl[0]);
    //printf("%9.3f\n", translation_vectors15->data.fl[1]);
    //printf("%9.3f\n", translation_vectors15->data.fl[2]);
    //Get rotation_matrix from rotation_vectors
    cvRodrigues2(rotation_vectors15, rotation_matrix15, 0);
    /*for(i=0; i<3; i++)
        for(j=0; j<3; j++)
            printf("%9.3f ", (float)cvGetReal2D(rotation_matrix15, i, j));*/
    //PrintMat(intrinsic_matrix, FALSE, NULL);
    //PrintMat(distortion_coeffs, FALSE, NULL);
    //Save our work
    cvSave("Intrinsics.xml", intrinsic_matrix);
    cvSave("Distortion.xml", distortion_coeffs);
    cvSave("Rotation_V.xml", rotation_vectors15);
    cvSave("Translation.xml", translation_vectors15);
    cvSave("Rotation_M.xml", rotation_matrix15);
    //cvSave("Rotation.xml",rotation_vectors);
    //cvSave("Translation.xml",translation_vectors);
    //Load test
    //CvMat *intrinsic = (CvMat*)cvLoad("Intrinsics.xml");
    //CvMat *distortion = (CvMat*)cvLoad("Distortion.xml");

    //Build the undistort map which we will use for all
    //subsequent frames.
    //
    /*IplImage* mapx = cvCreateImage(cvGetSize(image), IPL_DEPTH_32F, 1);
    IplImage* mapy = cvCreateImage(cvGetSize(image), IPL_DEPTH_32F, 1);
    printf("cvInitUndistortMap\n");
    cvInitUndistortMap(
        intrinsic,
        distortion,
        mapx,
        mapy
    );
    // Just run the camera to the screen, now only showing the undistorted
    // image.
    //
    rewind(fptr);
    cvNamedWindow("Undistort");
    printf("\n\nPress any key to step through the images, ESC to quit\n\n");
    while(fscanf(fptr, "%s ", names)==1)
    {
        if(image)
        {
            cvReleaseImage(&image);
            image = 0;
        }
        image = cvLoadImage(names);
        IplImage *t = cvCloneImage(image);
        cvShowImage("Calibration", image);
        cvRemap(t, image, mapx, mapy);
        cvReleaseImage(&t);
    //	  cvUndistort2(
        cvShowImage("Undistort", image);
        if((cvWaitKey()&0x7F) == 27)
            break;
    }
    printf("Camera intrinsic matrix:\n");*/
}


void CHW1Dlg::OnBnClickedButton4()
{
    // TODO: 在此添加控件通知处理程序代码
    cout<<"Find the distortion matrix:";
    printf("[%.3f,%.3f,%.3f,%.3f,%.3f]\n", k1, k2, p1, p2, k3);
}


void CHW1Dlg::OnBnClickedButton5()
{
    // TODO: 在此添加控件通知处理程序代码
    cout<<"Find the extrinsic matrix of chessboard-c15:\n";
    CvMat* rotation_matrix15_2  = (CvMat*)cvLoad("Rotation_M.xml");
    CvMat* translation_vectors15_2 = (CvMat*)cvLoad("Translation.xml");
    int i=0;
    int j;
    printf("[");
    for(j=0; j<3; j++)
    {
        printf("%6.3f ", (float)cvGetReal2D(rotation_matrix15_2, i, j));
    }
    printf("%6.3f;\n", (float)cvGetReal2D(translation_vectors15_2, i, 0));
    i++;
    printf(" ");
    for(j=0; j<3; j++)
    {
        printf("%6.3f ", (float)cvGetReal2D(rotation_matrix15_2, i, j));
    }
    printf("%6.3f;\n", (float)cvGetReal2D(translation_vectors15_2, i, 0));
    i++;
    printf(" ");
    for(j=0; j<3; j++)
    {
        printf("%6.3f ", (float)cvGetReal2D(rotation_matrix15_2, i, j));
    }
    printf("%6.3f]\n", (float)cvGetReal2D(translation_vectors15_2, i, 0));
}


void CHW1Dlg::OnBnClickedButton6()
{
    // TODO: 在此添加控件通知处理程序代码
    cout<<"Draw three axis of the chessboard-c15.\n";
    CvMat* intrinsic_matrix2 = (CvMat*)cvLoad("Intrinsics.xml");
    CvMat* distortion_coeffs2 = (CvMat*)cvLoad("Distortion.xml");
    CvMat* rotation_vectors15_2 = (CvMat*)cvLoad("Rotation_V.xml");
    CvMat* translation_vectors15_3 = (CvMat*)cvLoad("Translation.xml");
    //Define objectPoints and imagePoints
    std::vector<cv::Point3f> objectPoints;
    std::vector<cv::Point2f> imagePoints;
    //Add four points
    objectPoints.push_back(cv::Point3f(0.0,0.0,0.0));
    objectPoints.push_back(cv::Point3f(2.0,0.0,0.0));
    objectPoints.push_back(cv::Point3f(0.0,2.0,0.0));
    objectPoints.push_back(cv::Point3f(0.0,0.0,-2.0));
    cv::projectPoints(objectPoints, (Mat)rotation_vectors15_2, (Mat)translation_vectors15_3, (Mat)intrinsic_matrix2, (Mat)distortion_coeffs2, imagePoints);
    //Draw three axis
    cvLine(imgRGB2, (cvPoint)(corners[0].x, corners[0].y), (cvPoint)(imagePoints[1].x, imagePoints[1].y), Blue, 5, CV_AA, 0);
    cvLine(imgRGB2, (cvPoint)(corners[0].x, corners[0].y), (cvPoint)(imagePoints[2].x, imagePoints[2].y), Blue, 5, CV_AA, 0);
    cvLine(imgRGB2, (cvPoint)(corners[0].x, corners[0].y), (cvPoint)(imagePoints[3].x, imagePoints[3].y), Red, 5, CV_AA, 0);
    cvNamedWindow("coordinate");
    cvShowImage("coordinate", imgRGB2);
}


