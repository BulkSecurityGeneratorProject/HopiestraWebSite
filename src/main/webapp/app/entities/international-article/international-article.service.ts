import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { InternationalArticle } from './international-article.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<InternationalArticle>;

@Injectable()
export class InternationalArticleService {

    private resourceUrl =  SERVER_API_URL + 'api/international-articles';

    constructor(private http: HttpClient) { }

    create(internationalArticle: InternationalArticle): Observable<EntityResponseType> {
        const copy = this.convert(internationalArticle);
        return this.http.post<InternationalArticle>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(internationalArticle: InternationalArticle): Observable<EntityResponseType> {
        const copy = this.convert(internationalArticle);
        return this.http.put<InternationalArticle>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<InternationalArticle>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<InternationalArticle[]>> {
        const options = createRequestOption(req);
        return this.http.get<InternationalArticle[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<InternationalArticle[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: InternationalArticle = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<InternationalArticle[]>): HttpResponse<InternationalArticle[]> {
        const jsonResponse: InternationalArticle[] = res.body;
        const body: InternationalArticle[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to InternationalArticle.
     */
    private convertItemFromServer(internationalArticle: InternationalArticle): InternationalArticle {
        const copy: InternationalArticle = Object.assign({}, internationalArticle);
        return copy;
    }

    /**
     * Convert a InternationalArticle to a JSON which can be sent to the server.
     */
    private convert(internationalArticle: InternationalArticle): InternationalArticle {
        const copy: InternationalArticle = Object.assign({}, internationalArticle);
        return copy;
    }
}
