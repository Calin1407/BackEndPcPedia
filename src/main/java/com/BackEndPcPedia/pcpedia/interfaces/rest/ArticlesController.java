package com.BackEndPcPedia.pcpedia.interfaces.rest;

import com.BackEndPcPedia.pcpedia.application.dto.ArticlesRequest;
import com.BackEndPcPedia.pcpedia.application.dto.ArticlesResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Articles;
import com.BackEndPcPedia.pcpedia.domain.model.commands.RegisterArticleCommand;
import com.BackEndPcPedia.pcpedia.domain.model.commands.UpdateArticleCommand;
import com.BackEndPcPedia.pcpedia.domain.model.commands.handler.RegisterArticleHandler;
import com.BackEndPcPedia.pcpedia.domain.model.commands.handler.UpdateArticleHandler;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetArticleByIdQuery;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetListArticlesQuery;
import com.BackEndPcPedia.pcpedia.domain.model.queries.Handler.GetArticleByIdHandler;
import com.BackEndPcPedia.pcpedia.domain.model.queries.Handler.GetListArticleHandler;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.ArticlesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    private final RegisterArticleHandler registerArticleHandler;
    private final UpdateArticleHandler updateArticleHandler;
    private final GetArticleByIdHandler getArticleByIdHandler;
    private final GetListArticleHandler getListArticleHandler;

    public ArticlesController(RegisterArticleHandler _create,
                              UpdateArticleHandler _update,
                              GetArticleByIdHandler _getArt,
                              GetListArticleHandler _getList){
        this.registerArticleHandler = _create;
        this.updateArticleHandler = _update;
        this.getArticleByIdHandler = _getArt;
        this.getListArticleHandler = _getList;
    }

    @PostMapping
    public ResponseEntity<ArticlesResponse> create(@RequestBody ArticlesRequest body){
        var res = registerArticleHandler.handle(
                new RegisterArticleCommand(body.name(),body.description(),body.category(),body.price()));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticlesResponse> update(@PathVariable Long id,@RequestBody ArticlesRequest body){
        var res = updateArticleHandler.handle(
                new UpdateArticleCommand(id, body.name(), body.description(), body.category(), body.price()));
        return ResponseEntity.ok(res);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ArticlesResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(getArticleByIdHandler.handle(new GetArticleByIdQuery(id)));
    }

    @GetMapping
    public ResponseEntity<Iterable<ArticlesResponse>> list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category
    ){
        return ResponseEntity.ok(getListArticleHandler.handle(new GetListArticlesQuery
                (page, pageSize, status, category)));
    }
}
