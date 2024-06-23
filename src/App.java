import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
public class App extends JFrame
 {
    MenuItem playlist,likedList,recent;
    Menu subMenu;
    MenuBar mainMenu;
    Background bg;
    App() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        super("Music PLayer");
        setSize(630,513);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bg = new Background();
        setResizable(false);
        add(bg);
        // creating main menu
        mainMenu = new MenuBar();
        subMenu  =  new Menu("MUSIC");
        playlist = new MenuItem("Playlist");
        recent = new MenuItem("recent");
        likedList = new MenuItem("Liked List");
        mainMenu.setFont(new Font("Comic Sans MS", 4, 30));
        mainMenu.add(subMenu);
        subMenu.add(playlist);
        subMenu.add(likedList);
        subMenu.add(recent);
        setMenuBar(mainMenu);
        setVisible(true);
    }
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException  {
        new App();
    }
    
}
class Background extends JPanel implements ActionListener
{
    ImageIcon bgimg,music ;
    MusicPlayer Player ;
    features charge;
    JLabel label,title,musiclabel,songTitle,author,duration;
    JSlider seekbar;
    JButton play,pause,next,previous,suffle,back,front,search;
    private void setStyle(JComponent comp,Font f,Color fg,Color bg)
    {
        comp.setFont(f);
        comp.setForeground(fg);
        comp.setBackground(bg);
    }
    Background() throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        // creating component
        try{
        this.bgimg = new ImageIcon("bgimg.png");
        this.music = new ImageIcon("music.jpg");
        }
        catch(Exception e){
            System.out.println("img not found");
        }
        this.label = new JLabel(this.bgimg);
        this.musiclabel = new JLabel(this.music);
        this.title = new JLabel("Music Player");
        this.songTitle =  new JLabel("Song Title");
        this.author = new JLabel("author");
        this.duration = new JLabel("Duration");
        this.Player =new MusicPlayer( "C:\\Users\\Aaditya Kumar\\Downloads\\Shiva_Tandava_Stotram.wav");
        
        this.seekbar = new JSlider(0,(int) this.Player.musicLength,10);
        this.charge = new features(this.seekbar);
        try{
        this.play = new JButton(new ImageIcon("play.jpg"));
        this.pause = new JButton(new ImageIcon("pause.png"));
        this.next = new JButton(new ImageIcon("next.jpg"));
        this.previous = new JButton(new ImageIcon("previous.png"));
        this.suffle = new JButton(new ImageIcon("suffle.png"));
        this.back = new JButton(new ImageIcon("30++.png"));
        this.front = new JButton(new ImageIcon("30--.png"));
        this.search = new JButton(new ImageIcon("search.png"));
        }catch (Exception e){System.out.println("img not found");}
        // functioanlity to components
        this.seekbar.setPaintTrack(true);
        this.seekbar.setPaintTicks(true);
        this.seekbar.setPaintLabels(true);
        setStyle(this.title,new Font("Comic Sans MS", 4, 30),Color.CYAN,null);
        setStyle(this.songTitle,new Font("Comic Sans MS",2,15),Color.CYAN,null);
        setStyle(this.author,new Font("Comic Sans MS",2,15),Color.CYAN,null);
        setStyle(this.duration,new Font("Comic Sans MS",2,15),Color.CYAN,null);
        this.seekbar.setOpaque(false);
        this.play.addActionListener(this);
        this.pause.addActionListener(this);
        this.back.addActionListener(this);
        this.front.addActionListener(this);
        this.search.addActionListener(this);
        //positioning component
        label.setBounds(0,0,442,334);
        this.title.setBounds(250,-20,250,100);
        this.musiclabel.setBounds(150,90,96,109);
        this.songTitle.setBounds(150,160,120,100);
        this.author.setBounds(150,180,120,100);
        this.duration.setBounds(150,200,120,100);
        this.suffle.setBounds(150,390,50,50);
        this.back.setBounds(205,390,50,50);
        this.previous.setBounds(255,390,50,50);
        this.play.setBounds(310,390,50,50);
        this.pause.setBounds(365,390,50,50);
        this.next.setBounds(420,390,50,50);
        this.front.setBounds(475,390,50,50);
        this.seekbar.setBounds(50,330,500,50);
        this.search.setBounds(0,0,50,50);
        // adding the compon,ents
        add(label);
        label.add(this.title);
        label.add(this.musiclabel);
        label.add(this.songTitle);
        label.add(this.author);
        label.add(this.duration);
        label.add(this.play);
        label.add(this.pause);
        label.add(this.next);
        label.add(this.front);
        label.add(this.previous);
        label.add(this.back);
        label.add(this.suffle);
        label.add(this.seekbar);
        label.add(this.search);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==play )
        {
                try {
                    this.Player.playAudio();
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }
            this.Player.status = "PLAY";
        }
        if(e.getSource()==pause )
        {
                try {
                    this.Player.pauseAudio();
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }
            this.Player.status = "PAUSE";
        }
        if(e.getSource()==back)
        {
                try {
                    this.Player.shiftback();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }
        }
        if(e.getSource()==front)
        {
                try {
                    this.Player.shiftfront();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }
        }
        if(e.getSource()==search)
        {
            try {
                this.Player.closeAudio();
                this.Player=this.charge.search(this.Player);
                this.Player.resetAudio();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }
        }
    }
    class features implements ChangeListener
    {
        JSlider slide;
        features(JSlider s)
        {
            this.slide =s;
        }
        void library(){

        }
        void likedList() {}// like button
        MusicPlayer search(MusicPlayer currentFile) throws IOException, LineUnavailableException, UnsupportedAudioFileException{
            JFileChooser choose = new JFileChooser("C:\\Users\\Aaditya Kumar\\Desktop\\App.java");
            int state =choose.showOpenDialog(null);
            if(state == JFileChooser.APPROVE_OPTION)
            {
                return (new MusicPlayer(choose.getSelectedFile().getAbsolutePath()));
            }
            if(state == JFileChooser.CANCEL_OPTION)
            {
                return (currentFile);
            }
            if(state == JFileChooser.ERROR_OPTION)
            {
                return (currentFile);
            }
            return null;
        }
        void playlist(){
            System.out.println("executed");
        }
        void recent(){}
        void controllMusicSlider(){
            this.slide.addChangeListener(this);
        }
        public void stateChanged(ChangeEvent e)
        {
            System.out.println(this.slide.getValue());
        }
    } 
    class MusicPlayer
    {
        public float musicLength;
        private static AudioFormat audioFormat;
        AudioInputStream invoke;
        Clip musicFile;
        String status ;
        long currentPosition;
        String currentFile;
        MusicPlayer(String filename) throws  
        IOException, LineUnavailableException, UnsupportedAudioFileException {
            this. invoke = AudioSystem.getAudioInputStream(new File(filename));
            this.musicFile = AudioSystem.getClip();
            musicFile.open(this.invoke);
            musicFile.loop(Clip.LOOP_CONTINUOUSLY);
            pauseAudio();   
            this.currentPosition =musicFile.getMicrosecondPosition(); 
            this.status = "PAUSE";
            this.currentFile = filename;
            audioFormat = musicFile.getFormat();
            this.musicLength = musicFile.getFrameLength()/audioFormat.getFrameRate();
            } 
        public void closeAudio() {
            musicFile.close();
        }
        public void shiftback() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
            musicFile.close();
            resetAudio();
            this.currentPosition = (this.currentPosition+(30000000));
            musicFile.setMicrosecondPosition(this.currentPosition);
            musicFile.start();
            System.out.println(musicFile.getMicrosecondPosition());
        }
        public void shiftfront() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
            musicFile.close();
            resetAudio();
            this.currentPosition = (this.currentPosition-(30000000));
            musicFile.setMicrosecondPosition((this.currentPosition));
            musicFile.start();
            System.out.println(musicFile.getMicrosecondPosition());
        }
        
        public void resetAudio() throws  
        IOException, LineUnavailableException, UnsupportedAudioFileException  {
            this.invoke =null;
            this.musicFile = null;
            this. invoke = AudioSystem.getAudioInputStream(new File(currentFile));
            this.musicFile = AudioSystem.getClip();
            musicFile.open(this.invoke);
            musicFile.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println(musicFile.getMicrosecondPosition());
        }
        public static String convertTOWavFile(String inputfile,String outputfile) throws IOException, UnsupportedAudioFileException{
            AudioInputStream input =  AudioSystem.getAudioInputStream(new File(inputfile));
            // converting format of file
            audioFormat = input.getFormat();
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,audioFormat.getSampleRate(), 16, audioFormat.getChannels(),
                            audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
            AudioInputStream output = AudioSystem.getAudioInputStream(format,input);
            //writting the file
            AudioSystem.write(input, AudioFileFormat.Type.WAVE, new File(outputfile));
            output.close();
            input.close();
            return outputfile;
        }
        void playAudio() throws UnsupportedAudioFileException,LineUnavailableException
        {
            musicFile.setMicrosecondPosition(this.currentPosition);
            musicFile.start();
            System.out.println(musicFile.getMicrosecondPosition());
        }
        void pauseAudio() throws UnsupportedAudioFileException,LineUnavailableException
        {
            musicFile.stop();
            this.currentPosition =  musicFile.getMicrosecondPosition();
            musicFile.setMicrosecondPosition(this.currentPosition);
            System.out.println(musicFile.getMicrosecondPosition());
        }
    }
}