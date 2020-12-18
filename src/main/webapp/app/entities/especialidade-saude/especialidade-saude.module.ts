import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicaAdaptrojhAppSharedModule } from 'app/shared/shared.module';
import { EspecialidadeSaudeComponent } from './especialidade-saude.component';
import { EspecialidadeSaudeDetailComponent } from './especialidade-saude-detail.component';
import { EspecialidadeSaudeUpdateComponent } from './especialidade-saude-update.component';
import { EspecialidadeSaudeDeleteDialogComponent } from './especialidade-saude-delete-dialog.component';
import { especialidadeSaudeRoute } from './especialidade-saude.route';
import { MyAdaptroUtilsService } from 'app/shared/util/my-adaptro-util.service';

@NgModule({
  imports: [ClinicaAdaptrojhAppSharedModule, RouterModule.forChild(especialidadeSaudeRoute)],
  declarations: [
    EspecialidadeSaudeComponent,
    EspecialidadeSaudeDetailComponent,
    EspecialidadeSaudeUpdateComponent,
    EspecialidadeSaudeDeleteDialogComponent,
  ],
  entryComponents: [EspecialidadeSaudeDeleteDialogComponent],
  providers: [MyAdaptroUtilsService],
})
export class ClinicaAdaptrojhAppEspecialidadeSaudeModule {}
