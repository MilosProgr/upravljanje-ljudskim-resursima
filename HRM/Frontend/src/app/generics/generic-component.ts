import { Component, Input, OnInit } from '@angular/core';
import { CrudService } from './generic-service';
import { Resourse } from './generic_hateoas/resourse';


@Component({
    template: ''
})
export abstract class GenericCrudComponent<T> implements OnInit {
    @Input() entities: T[] = [];
    selectedEntity: T | null = null;

    pageNumber = 0;
    pageSize = 10;
    totalPages = 0;
    totalElements = 0;

    constructor(
        private crudService: CrudService<T>,
    ) { }

    ngOnInit(): void {
        this.getAllEntities();
    }

    getAllEntities() {
        this.crudService.getAll().subscribe(entities => {
            this.entities = entities;
        });
    }

    create(formValues: any) {
        if (!formValues) return;

        const data = formValues;
        if (data.id) {
            // Update existing record
            this.crudService.update(data.id, data).subscribe(() => this.getAllEntities());
        } else {
            // Create new record
            this.crudService.create(data).subscribe(() => this.getAllEntities());
        }
    }

    delete(id: number) {
        this.crudService.delete(id).subscribe(() => this.getAllEntities());
    }

    edit(entity: T) {
        this.selectedEntity = { ...entity };
    }
    // HATEOAS / Paged
    loadPagedEntities(): void {
        this.crudService.getPaged(this.pageNumber, this.pageSize)
            .subscribe(res => {

                const page = res.data; // 🔥 KLJUČNO

                this.entities = Array.isArray(page?.content) ? page.content : [];

                this.entities = page.content;
                this.pageNumber = page.pageNumber;
                this.pageSize = page.pageSize;
                this.totalPages = page.totalPages;
                this.totalElements = page.totalElements;
            });
    }




    loadResource(id: number): void {
        this.crudService.getResource(id).subscribe((res: Resourse<T>) => {
            this.selectedEntity = res.data;
            // Ako želiš, možeš iskoristiti res.links za navigaciju
        });
    }

    goToPage(page: number): void {
        this.pageNumber = page;
        this.loadPagedEntities();
    }
}
