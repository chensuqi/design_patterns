package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 9:39
 * @desc 适配器模式
 */
public class AdapterPattern {
    public static void main(String[] args) {
        MediaAdapter adapter = new MediaAdapter();
        adapter.play("mp4","abc.mp4");
        adapter.play("vlc","vlc123.vlc");
        adapter.play("mp3","mpc.mp3");
    }
}
interface MediaPlayer {
     void play(String audioType, String fileName);
}
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }
    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}

class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer = new VlcPlayer();
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
            advancedMusicPlayer.playMp4(fileName);
        } else {
            System.out.println("暂时没有适配播放器");
        }
    }
}