package site.isscloud.xdclass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import site.isscloud.xdclass.model.entity.Chapter;
import site.isscloud.xdclass.model.entity.Video;
import site.isscloud.xdclass.model.entity.VideoBanner;
import site.isscloud.xdclass.exception.BizException;
import site.isscloud.xdclass.service.ChapterService;
import site.isscloud.xdclass.service.VideoService;
import site.isscloud.xdclass.utils.JsonData;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pub/video")
public class VideoController {
    private final VideoService videoService;
    private final ChapterService chapterService;

    public VideoController(VideoService videoService, ChapterService chapterService) {
        this.videoService = videoService;
        this.chapterService = chapterService;
    }

    @GetMapping("/list")
    public JsonData listVideo() {
        List<Video> list = videoService.listVideo();
        return JsonData.buildSuccess(list);
    }

    @GetMapping("/list/page")
    public JsonData listVideoPage(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                  @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Video> list = videoService.listVideo();
        PageInfo<Video> pageInfo = new PageInfo<Video>(list);
        System.out.println(pageInfo);
        return JsonData.buildSuccess(pageInfo);
    }

    @GetMapping("/banner")
    public JsonData listIndexBanner() {
        List<VideoBanner> list = videoService.listIndexBanner();
        return JsonData.buildSuccess(list);
    }

    /**
     * 根据id获得Video完整详情信息，包括:video,chapter,episode
     * @param id video的id
     * @return
     */
    @GetMapping("/{id}/detail")
    public JsonData findVideoDetailById(@PathVariable("id") int id) {
        Video video = videoService.getVideoDetailByIdLazy(id,3);
        return JsonData.buildSuccess(video);
    }

    /**
     * 根据video的id即depth深度，来获得Video完整详情信息，是否包括:chapter,episode有depth决定
     * @param id video的id
     * @param depth 深度，默认为1（video), 2(chapter), 3(Episode)
     * @return
     */
    @GetMapping("/{id}")
    // 注意：@PathVariable("id")，代表动态参数，即可以放到url路径中的参数
    public JsonData findVideoById(@PathVariable("id")Integer id,@RequestParam(required = false) Integer depth) {
        Video video = videoService.getVideoDetailByIdLazy(id,depth);
        return JsonData.buildSuccess(video);
    }

    /**
     * 根据video的id获取章节详细列表，是否包含episode根据depth来决定
     * @param id video的ID
     * @param depth 1:仅chapter 2:包含Episode
     * @return
     */
    @GetMapping("/{id}/chapters")
    public JsonData selectChaptersById(@PathVariable("id")Integer id,@RequestParam(required = false) Integer depth){
        List<Chapter> list = chapterService.selectChaptersDetailByVideoIdLazy(id,depth);
        return JsonData.buildSuccess(list);
    }

    /**
     * 根据video的id获取章节详细列表，包含episode
     * @param id video的ID
     * @return
     */
    @GetMapping("/{id}/chapters/detail")
    public JsonData selectChaptersDetailById(@PathVariable("id")Integer id) {
        List<Chapter> list = chapterService.selectChaptersDetailByVideoIdLazy(id,2);
        return JsonData.buildSuccess(list);
    }
}
