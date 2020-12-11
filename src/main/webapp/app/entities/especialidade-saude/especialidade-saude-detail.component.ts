import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';

@Component({
  selector: 'jhi-especialidade-saude-detail',
  templateUrl: './especialidade-saude-detail.component.html',
})
export class EspecialidadeSaudeDetailComponent implements OnInit {
  especialidadeSaude: IEspecialidadeSaude | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ especialidadeSaude }) => (this.especialidadeSaude = especialidadeSaude));
  }

  previousState(): void {
    window.history.back();
  }
}
