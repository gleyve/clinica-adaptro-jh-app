import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanoEstrategico } from 'app/shared/model/plano-estrategico.model';

@Component({
  selector: 'jhi-plano-estrategico-detail',
  templateUrl: './plano-estrategico-detail.component.html',
})
export class PlanoEstrategicoDetailComponent implements OnInit {
  planoEstrategico: IPlanoEstrategico | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ planoEstrategico }) => (this.planoEstrategico = planoEstrategico));
  }

  previousState(): void {
    window.history.back();
  }
}
