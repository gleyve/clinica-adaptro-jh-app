import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPlanoEstrategico } from 'app/shared/model/plano-estrategico.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PlanoEstrategicoService } from './plano-estrategico.service';
import { PlanoEstrategicoDeleteDialogComponent } from './plano-estrategico-delete-dialog.component';

@Component({
  selector: 'jhi-plano-estrategico',
  templateUrl: './plano-estrategico.component.html',
})
export class PlanoEstrategicoComponent implements OnInit, OnDestroy {
  planoEstrategicos: IPlanoEstrategico[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected planoEstrategicoService: PlanoEstrategicoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.planoEstrategicos = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.planoEstrategicoService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IPlanoEstrategico[]>) => this.paginatePlanoEstrategicos(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.planoEstrategicos = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPlanoEstrategicos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPlanoEstrategico): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPlanoEstrategicos(): void {
    this.eventSubscriber = this.eventManager.subscribe('planoEstrategicoListModification', () => this.reset());
  }

  delete(planoEstrategico: IPlanoEstrategico): void {
    const modalRef = this.modalService.open(PlanoEstrategicoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.planoEstrategico = planoEstrategico;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePlanoEstrategicos(data: IPlanoEstrategico[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.planoEstrategicos.push(data[i]);
      }
    }
  }
}
