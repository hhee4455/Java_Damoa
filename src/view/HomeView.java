package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.Controller;
import model.ChatRoomEntity;
import model.Model;
import model.PostEntity;
import view.ChatListView.ImageLabelItem;
import view.ChatListView.ImageLabelListCellRenderer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.JToolBar;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class HomeView extends JPanel {
	
	private static Container contentPane;
	private  Controller controller;
	private  Model model;
	private JTextField textField;
	
	public HomeView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(Color.white);
		setLayout(null);
		
		
		
		Centerbtn();
		
		btnPanel();
		
	}
	
	private void Centerbtn() {
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 400, 429);
		scrollPane.setBorder(null);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		DefaultListModel<ImageLabelItem> listModel = new DefaultListModel<>();

		List<PostEntity> posts = model.getAllPosts();
	    
		for (PostEntity post : posts) {
	        ImageIcon imageIcon = new ImageIcon(post.getImage());

	        // 이미지를 원하는 크기로 조정합니다.
	        Image scaledImage = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

	        // ImageLabelItem 객체를 생성하여 ImageIcon과 추가 정보를 저장합니다.
	        ImageLabelItem item = new ImageLabelItem(scaledImageIcon, post.getTitle());

	        // 리스트 모델에 ImageLabelItem을 추가합니다.
	        listModel.addElement(item);
	    }
	    
	    // JList를 생성하고 리스트 모델을 설정합니다.
	    JList<ImageLabelItem> list = new JList<>(listModel);
	    list.setCellRenderer(new ImageLabelListCellRenderer());
	    
	    
	    list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = list.getSelectedIndex();
                if (model.isLoggedin()) { // Check if the user is logged in
                    PostEntity selectedPost = posts.get(index);
                    model.setCurrentPost(selectedPost);
                    controller.showCard("post");
                } else {
                	controller.showCard("login");
                }
            }
        });

		scrollPane.setViewportView(list);
		
	    JLabel lblNewLabel_2 = new JLabel("");
	    lblNewLabel_2.setBounds(360, 25, 20, 20);
	    ImageIcon categoryicon = new ImageIcon("image/카테고리.png");
	    Image imgcategory = categoryicon.getImage();
	    Image imgcategory2 = imgcategory.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	    ImageIcon iconcategory2 = new ImageIcon(imgcategory2);
	    lblNewLabel_2.setIcon(iconcategory2);
	    add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setBounds(330, 25, 20, 20);
	    ImageIcon searchicon = new ImageIcon("image/돋보기.png");
	    Image imgsearch = searchicon.getImage();
	    Image imgsearch2 = imgsearch.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	    ImageIcon iconsearch2 = new ImageIcon(imgsearch2);
	    lblNewLabel_1.setIcon(iconsearch2);
	    add(lblNewLabel_1);
	    
	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setBackground(new Color(228, 204, 255));
	    lblNewLabel.setBounds(0, 0, 400, 70);
	    ImageIcon damoaicon = new ImageIcon("image/상단바.jpg");
	    Image imgdamoa = damoaicon.getImage();
	    Image imgdamoa2 = imgdamoa.getScaledInstance(400, 70, Image.SCALE_SMOOTH);
	    ImageIcon icondamoa2 = new ImageIcon(imgdamoa2);
	    lblNewLabel.setIcon(icondamoa2);
	    add(lblNewLabel);
	    
	    textField = new JTextField();
	    textField.setFont(new Font("맑은 고딕", Font.BOLD, 13));
	    textField.setBorder(null);
	    textField.setBounds(100, 25, 200, 20);
	    add(textField);
	    textField.setColumns(10);

	}
	
	private void btnPanel() {
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	    panel1.setBackground(new Color(255, 255, 255));
	    panel1.setBounds(0, 500, 400, 70);
	    add(panel1);

	    JLabel lblHome = new JLabel();
	    lblHome.setBounds(0, 0, 100, 70);
	    lblHome.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	    ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
	    Image imghome = homeicon.getImage();
	    Image imghome2 = imghome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgicon2 = new ImageIcon(imghome2);
	    panel1.setLayout(null);
	    lblHome.setIcon(imgicon2);
	    lblHome.setHorizontalAlignment(SwingConstants.CENTER);
	    lblHome.setBackground(new Color(201, 219, 178));
	    panel1.add(lblHome);
	    lblHome.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            controller.showCard("home"); // 라벨 클릭 시 홈 화면 보여줌
	        }
	    });
	    
	    JLabel lblRecruitment = new JLabel();
	    lblRecruitment.setBounds(100, 0, 100, 70);
	    lblRecruitment.setBorder(null);
	    ImageIcon posticon = new ImageIcon("image/postbutton3.png");
	    Image imgpost = posticon.getImage();
	    Image imgpost2 = imgpost.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon posticon2 = new ImageIcon(imgpost2);
		lblRecruitment.setIcon(posticon2);
	    lblRecruitment.setHorizontalAlignment(SwingConstants.CENTER);
	    lblRecruitment.setBackground(new Color(201, 219, 178));
	    panel1.add(lblRecruitment);
	    lblRecruitment.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	            controller.showCard("postform"); // 라벨 클릭 시 채팅 화면 보여줌
	        }
	    });

	    
	    JLabel lblChat = new JLabel();
	    lblChat.setBounds(200, 0, 100, 70);
	    lblChat.setBorder(new LineBorder(new Color(192, 192, 192)));
	    ImageIcon chaticon = new ImageIcon("image/chatbutton.png");
	    Image imgchat = chaticon.getImage();
	    Image imgchat2 = imgchat.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconchat2 = new ImageIcon(imgchat2);
		lblChat.setIcon(iconchat2);
	    lblChat.setHorizontalAlignment(SwingConstants.CENTER);
	    lblChat.setBackground(new Color(201, 219, 178));
	    panel1.add(lblChat);
	    lblChat.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            controller.showCard("chatlist"); // 라벨 클릭 시 채팅 화면 보여줌
	        }
	    });

	    JLabel lblMypage = new JLabel();
	    lblMypage.setBounds(300, 0, 100, 70);
	    ImageIcon mypageicon = new ImageIcon("image/mypage.png");
	    Image imgmypage = mypageicon.getImage();
	    Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
		lblMypage.setIcon(iconmypage2);
	    lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
	    lblMypage.setBackground(new Color(192, 192, 192));
	    panel1.add(lblMypage);
	    lblMypage.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		controller.showCard("login");  
	        }
	    });
	}
	
    class ImageLabelItem {
    	private ImageIcon image;
    	private String label;
    	
    	public ImageLabelItem(ImageIcon image, String label) {
    		this.image = image;
    		this.label = label;	   
    	}
    	
    	public String getLabel() {
    		return label;
    	}
    }
    
    class ImageLabelListCellRenderer extends JPanel implements ListCellRenderer<ImageLabelItem> {
    	private JLabel imageLabel;
    	private JLabel textLabel;
    	
    	public ImageLabelListCellRenderer() {
    		setLayout(new BorderLayout());
    		setOpaque(true);
    		
    		imageLabel = new JLabel();
    		textLabel = new JLabel();
    		
    		add(imageLabel, BorderLayout.WEST);
    		add(textLabel, BorderLayout.CENTER);
    	}
    	
    	@Override
    	public Component getListCellRendererComponent(JList<? extends ImageLabelItem> list, ImageLabelItem value, int index,
    			boolean isSelected, boolean cellHasFocus) {
    		imageLabel.setIcon(value.image);
    		textLabel.setText(value.getLabel());
    		
    		if (isSelected) {
    			setBackground(list.getSelectionBackground());
    			setForeground(list.getSelectionForeground());
    		} else {
    			setBackground(list.getBackground());
    			setForeground(list.getForeground());
    		}
    		if (index % 2 == 0) {
    			setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    		}
    		else {
    			setBorder(null);
    		}
    		return this;
    	}
    }
}
