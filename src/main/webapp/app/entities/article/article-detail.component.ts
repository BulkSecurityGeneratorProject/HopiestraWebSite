import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Article } from './article.model';
import { ArticleService } from './article.service';

@Component({
    selector: 'jhi-article-detail',
    templateUrl: './article-detail.component.html'
})
export class ArticleDetailComponent implements OnInit, OnDestroy {

    article: Article;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private articleService: ArticleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInArticles();
    }

    load(id) {
        this.articleService.find(id)
            .subscribe((articleResponse: HttpResponse<Article>) => {
                this.article = articleResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInArticles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'articleListModification',
            (response) => this.load(this.article.id)
        );
    }
}
