package site.isscloud.xdclass.config;

/**
 * 缓存KEY管理类
 */
public class CacheKeyManager {
    /**
     * 首页轮播图缓存KEY
     */
    public static final String INDEX_BANNER_KEY = "index:banner";

    /**
     * 首页视频列表缓存
     */
    public static final String INDEX_VIDEO_LIST_KEY = "index:video:list";

    /**
     * 视频详情的key, 第一个%s是视频id，第二个%s是视频的层级即depth,depth=1代表仅有视频信息，depth=2代表还有chapter信息，depth=3代表还有episode信息
     */
    public static final String VIDEO_DETAIL_KEY = "video:id_%s:depth_%s:detail";

    /**
     * 某视频章节详情key, 第一个%s是视频id，第二个%s是章节的层级即depth，depth=1代表仅有章节，depth=2代表还有episode
     */
    public static final String VIDEO_CHAPTERS_KEY = "video:id_%s:depth_%s:chapters";

    /**
     * 查找某章节详情key, 第一个%s是章节id，第二个%s是章节的层级即depth，depth=1代表仅有章节，depth=2代表还有episode
     */
    public static final String CHAPTER_DETAIL_KEY = "chapter:id_%s:depth_%s:detail";


}
