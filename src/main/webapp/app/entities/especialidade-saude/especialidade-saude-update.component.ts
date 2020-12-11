import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEspecialidadeSaude, EspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';
import { EspecialidadeSaudeService } from './especialidade-saude.service';

@Component({
  selector: 'jhi-especialidade-saude-update',
  templateUrl: './especialidade-saude-update.component.html',
})
export class EspecialidadeSaudeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descricao: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(70)]],
    sigla: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
    ativo: [],
  });

  constructor(
    protected especialidadeSaudeService: EspecialidadeSaudeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ especialidadeSaude }) => {
      this.updateForm(especialidadeSaude);
    });
  }

  updateForm(especialidadeSaude: IEspecialidadeSaude): void {
    this.editForm.patchValue({
      id: especialidadeSaude.id,
      descricao: especialidadeSaude.descricao,
      sigla: especialidadeSaude.sigla,
      ativo: especialidadeSaude.ativo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const especialidadeSaude = this.createFromForm();
    if (especialidadeSaude.id !== undefined) {
      this.subscribeToSaveResponse(this.especialidadeSaudeService.update(especialidadeSaude));
    } else {
      this.subscribeToSaveResponse(this.especialidadeSaudeService.create(especialidadeSaude));
    }
  }

  private createFromForm(): IEspecialidadeSaude {
    return {
      ...new EspecialidadeSaude(),
      id: this.editForm.get(['id'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
      sigla: this.editForm.get(['sigla'])!.value,
      ativo: this.editForm.get(['ativo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEspecialidadeSaude>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
