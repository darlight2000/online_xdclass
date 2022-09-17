package site.isscloud.xdclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.isscloud.xdclass.model.entity.Chapter;
import site.isscloud.xdclass.service.ChapterService;
import site.isscloud.xdclass.utils.JsonData;

@RestController
@RequestMapping("/api/v1/pub/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    /**
     * 根据chapter的id即层级depth来获取Chapter，是否包含episode由depth决定
     * @param id chapter的id
     * @param depth 层级，1为chapter 2包含Episode
     * @return
     */
    @GetMapping("/{id}")
    //注意： @RequestParam(value = "chapter_id") 做参数的别名
    public JsonData getChapterById(@PathVariable(value = "id") int id, @RequestParam(value = "depth",required = false) Integer depth){
        Chapter chapter = chapterService.getChapterDetailByIdLazy(id,depth);
        return JsonData.buildSuccess(chapter);
    }

    /**
     * 根据id获取Chapter详细信息，包含episode
     * @param id chapter的id
     * @return
     */
    @GetMapping("/{id}/detail")
    public JsonData getChapterDetailById(@PathVariable(value = "id") int id){
        Chapter chapter = chapterService.getChapterDetailByIdLazy(id,2);
        return JsonData.buildSuccess(chapter);
    }
}
