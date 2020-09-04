import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatDialog} from "@angular/material/dialog";
import {CountryDetailsComponent} from "../../country/country-details/country-details.component";


export class TableColumn {
  columnDef: string;
  header: string;
  cell: (_: any) => any;
  cellButton?: (_: any) => string;

  constructor(columnDef: string,
              header: string,
              cell: (_: any) => any,
              cellButton?: (_: any) => string) {
    this.columnDef = columnDef;
    this.header = header;
    this.cell = cell;
    this.cellButton = cellButton;
  }
}

@Component({
  selector: 'app-the-table',
  templateUrl: './the-table.component.html',
  styleUrls: ['./the-table.component.less']
})
export class TheTableComponent implements OnInit, AfterViewInit, OnChanges {
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @Input() tableColumn: TableColumn[];
  @Input() tableData: any[];

  columns: TableColumn[];
  displayedColumns: string[];

  dataSource: MatTableDataSource<any>;
  searchPlaceholder: String = 'Search Country';

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  openDialog() {
    const dialogRef = this.dialog.open(CountryDetailsComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  constructor(public dialog: MatDialog) {
  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource<any>(this.tableData);
    this.columns = this.tableColumn;
    this.displayedColumns = this.columns.map(c => c.columnDef);
    this.dataSource.sort = this.sort;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.tableData) {
      this.dataSource = new MatTableDataSource<any>(this.tableData);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    }
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
